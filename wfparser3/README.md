# WAVEFRONT PARSER VERSION 3


## Testing Methodology

The user acceptance tests define the behaviour of the system from the product owner's perspective.  They are used to
demonstrate the correct behaviour of the system.  The system is structured into several different subsystems.  The
most important subsystem are the domain objects defined in the "com.ht.wfp3.api.*" packages.  All other subsystems,
including the parser itself will be implemented based on these base subsystems.  The user acceptance tests are
written solely based on the published APIs of their respective subsystems.  This may result in duplication of testing
if the implementation results in shared classes that is not evident in the published APIs.  Similarly, the
implementation may result in classes or methods not visible from the published APIs.  Testing coverage analysis may
indicate that these implementation artifacts require additional tests to achieve sufficient testing coverage.  Any
such supplemental testing will be covered through unit testing.

In consideration of the extent of the coverage for the published APIs: The acceptance tests are defined by the user
stories acceptance criteria for visible behaviour, and all published API classes are visible, then any behaviour that
is associated with a published class is required to be defined by the acceptance tests, even if the user stories do
not explicitly state the behaviour in their acceptance criteria.

## Subsystems


### Subsystems: Common Status Reporting

The following description is just a set of guidelines for how I envision this subsystem to work.  Once you
implement the system, then delete this section since the software should be descriptive enough on its own.

This software system consists of a number of subsystems.  These subsystems require a common mechanism for reporting
status messages while they perform their tasks in such a way that is not as disruptive as throwing an exception.
These status messages may not be reported immediately as they may delayed and presented in a final report document
after the subsystem completes its task.

User stories require that the parser is supported at the UNIX command line.  This implies that the generated
messages are compatible with the standard UNIX command line.  Thus, the task name, priority name, and status
message must fit on a single standard UNIX terminal (80 characters at 12pts).  Thus, max lengths of:
   * task name: 10 characters
   * priority name: 10 characters
   * message: 50 characters
   * additional whitespace and formatting: 10 characters
   * overall line length (total of above): 80 characters

Alternatively, you may have an optional auto-wrap or simply ignore the line length limit.  I've certainly used
UNIX command line tools whose status messages are not limited to the standard terminal width.  But it would be
easier to simply start with the defined limit.  If the limits are implemented to be flexible, as they should be,
then they can easily be tweaked later.

Following are some relevant, high level usage scenarios.

Scenario: Remove status message during OBJ data model validation.
   1. Start OBJ data model validation.
   1. Create an empty OBJ validation report.
   1. Initialize status of GeoVertex statement to "not used" in validation report.
   1. Perform OBJ document validation task.
   1. Validation task determines GeoVertex statement is used by a Face statement.
   1. Remove "not used" status of GeoVertex statement.

Scenario: Parsing OBJ file requires linking status messages
   1. Parse OBJ file.
   1. Find GeoVertex that is incorrectly defined.
   1. Complete parsing.
   1. Report that "GeoVertex" statement at line number "XXX" is incorrectly defined because of specific reason.

In the above scenario, the strings in quotes would likely need to be parameterized.  Defining a different status
event for every statement may be too cumbersome.  What about "specific reason"?  To be more generic, you could
specify the character number, so it would be 'at character number "YYY"'.  At any rate, you'll likely want to
start with simple message reporting first, then enhance it with these features.

Scenario: Message system bootstrapping
   1. Message system is started with the client(s) specifying the configuration for their event messages.
   1. Message system initializes, possibly hard-coded, "primordial" messages for itself for messages related to the
      bootstrapping process.
   1. Message system reads the client configuration specified to it.
   1. Message system may generate bootstrapping messages if the proper bootstrapping events occur as set in its
      bootsrapping message configuration.

Since tasks, priorities, and status messages are defined during the reporting bootstrapping process, they can all
be checked to ensure they conform to the text width limitations, etc.  Alternatively, it may be sufficient for the
system to throw exceptions while bootstrapping if a non-recoverable error occurs.

Scenario: Validation requires report generation
   1. Parsing of OBJ file has completed and created OBJ document model successfully.
   1. Validation subsystem walks across OBJ document model to ensure valid relationships between statements defined
      on OBJ document model.
   1. Validation subsystem generates event messages based on information it determines while analyzing the OBJ
      document model and saves these event messages in a validation report.
   1. Validation subsystem completes validation of the OBJ document model.
   1. Validation subsystem publishes results of validation process in validation report.

It was mentioned above that a report is generated.  This will be a simple model, and it will be the responsibility
of some as of yet undetermined subsystem(s) to format the messages according to those subsystems' requirements.
However, tools for activities like sorting based on priority/message/etc. should be provided.  At the very least,
a default natural ordering should be provided.

The messages consist of the following data:
   * Unique message identifier
   * Topic
   * Description
   * Priority   
   * Associated object?: The message is optionally associated with an object from another subsystem.  Alternative 1:
        The tracking is performed by the client system.  As long as the message properly implements equals, hashcode,
        and compareTo, then the tracking can be done in standard maps, lists, etc.  Alternative 2: Do not implement
        removing messages at all, and just assume that message reports will be regenerated if the system state
        changes.  You should delay message removal as long as possible to see if it is actually needed or not.  If
        it is, then move to "alternative 1".

For the description, the intention is just to use "sprintf", so maybe read up about it, then figure out a simple
way to construct a formatted message that passing in all the required parameters.

All domain objects are implemented as immutable value objects.  The event handling would likely best be implemented
using a producer/consumer model.  Thus, if logging of events is desired, then a logging consumer could be
implemented that immediately logs events.  If a validation report generation is required, then the consumer would
generate the report by appending the messages to an appropriate, simple data structure.

It may be necessary to provide the ability to disable / enable events for scenarios like a given event was
incorrectly defined (or something) and should not be used anymore.

There are a number of problems with the localization implementation I currently have:
   * The fact that I need to write in the "undefined" configuration for each localized string is time consuming.
     But on the other hand, since it is defined, for all of the Priority, Topic, and Description objects, then
     I should not need to write it again.  Still, it must go into every defined localization otherwise it does
     not work.  This is the bigger problem since it makes the system fragile.  You should have a more robust and
     general mechanism for handling when a given locale configuration is missing on a string by string basis.
   * The localization functionality needs to know too much about the Priority, Topic, and Description objects
     since the localization functionality contains methods to localize all fields for each of these objects.  It
     would be better if the Priority, Topic, and Description classes implemented an interface published by the
     localization system for each of their fields that need to be localized.
   * you should consider re-implementing the localization so that their are three tiers.  The first tier is the
     user defined localization configuration(s) and it can be fully configuration based.  It is searched first for
     the localization data.  The second tier is the system tier and it is hard coded.  This is to get around the
     chicken and egg situation related to using the same interfaces that may require localized data from the
     "system" topic while setting up the "system".  The third and last tier is to handle scenarios when the
     localization data is missing from the two previous tiers.  Instead of halting the system due to a localization
     misconfiguration, you can just report that the localization data is missing.  This would be less prone to
     runtime errors.
   * I don't like Java properties files and never have.  You should implement the localization resource bundle data
     as a JSON file, or something, so that it has more structure and internal validation.  But how would it be
     extendible if someone wants to localize existing data to yet another locale?
   * perhaps the localization functionality should be moved to its own Java package, too?  It it more of a system
     level set of functionality and does not really belong to the message subsystem, conceptually.
   * You should also move all the localization related test cases from where they are into their own set of test
     cases.  Or maybe not?  Maybe you should separate between acceptance tests and unit tests, first, then see
     what should be where.  It would be acceptable to Mock the localization functionality when testing other things.
   * objects within the message system package that need to access the message factory should maintain a reference to
     the message system internal interface and NOT the message system interface.
   * I want some means to validate the localization at test time so that I can compare all defined field instances
     against the configured localization and know EXACTLY where each field is defined AND be able to easily compare
     to ensure it is defined as expected.
   * Not sure I like using a checked exception.  Finish implementation and review.  The objective of the new Localizer
     implementation is to ensure there are no unneeded exceptions during runtime.  This also applies to casting errors.
     From a reuse perspective, it makes more sense to use the standard, conventional Java casting exception instead
     of rolling your own.  But, the API implementation must check itself, early, right when the object passed in by
     the client to follow the "fail-fast" convention.  Thus the consequences of the bad client implementation are
     determined earlier rather than later.
   * WARNING: There could be overlap between different fields that LOOK distinct based on how the
     types and fields are named.  That is, the LocalizerType and LocalizerField instances are all
     unique but the fully qualified names for the fields can be exactly the same.  Since it is the
     FQDN that are used in the localization properties files, this is an issue.  You should solve this
     problem, but get things working, first.
   * Right now the Localizer interface setLocale() throws a LocalizerException, although the CompositeLocalizer
     class should never throw this exception since it will always set SOME localization bundle.  You should
     refactor to remove the throws clause from the published interface and have another internal method
     defined in the internal interface for the Localizer implementations to implement.  The internal class
     implementations should just throw an unsupported exception for their implementations of the published
     API since it does not make sense for them to implement them.  You cannot invert the inheritance
     hierarchy between the published and internal interfaces since this would cause the internal methods
     to be visible in the published API.
   * You should go through the implementation and mark method parameters as final to indicate that none of
     them will be modified.
   * You should also just look through the code for instances of duplication and remove them using
     inheritance, pulling out duplicate code into its own class and use composition, instead.
   * I want to create another small subsystem in a package called "com.ht.guard" that provides
     reusable guard code that can be used throughout all other subsystems, but I am having a
     difficult time figuring out how to structure the code so that the guard code supports optionally
     throwing exceptions, especially checked exceptions, without explicitly adding a generic
     "throws Exception" to the method interface.  I probably need to implement two interfaces within
     the subsystem.  One to mark a parameter to check for a specific condition.  Another to perform
     a specific action when that condition occurs.  The former method would never need to specify
     a throws clause.  The latter method would be implementation specific and might specify a
     throws clause.  I'm not sure that gets me any further ahead ...  Leave this until later.  If
     you do write this, make it a whitelist instead of a blacklist.  Also, you should set the
     naming convention you want to specify as compatible with Java package, class, and data field
     names for group, type, and field names respectively.  This would permit you to potentially
     defined annotations that automatically determined most of the data.  The only thing not set
     is the instance name which would be set in the annotation.  You should look at the Java
     validation framework.  It is an annotation based solution that you could potentially
     base your solution on.  At least some of the default annotations can be applied to
     method parameters.  Would be interesting to see if these or annotations of your own
     creation could be used.
   * You should consider adding yet another layer of testing: integration, which tests to ensure
     that independent subsystems of the whole solution work together as expected.  The testing
     should not be too extensive to reduce redundancy and should only indicate that ... what?
     Think about what the integration testing should prove in more concrete and limited terms.
   * There is an issue in the UID subsystem that will cause an integration problem when it is
     integrated with the Localizer subsystem.  The scenario where you try and recreate the same
     UID using "UidFactory.createUid(...)" twice in a row with the same parameters will fail the
     second time.  What it should do is return the same instance the second time that was created
     the first.
   * NOTE: While writing the StubUidFactory, it occurred to me that the primary difference between
     the StubUidFactory and the UidFactory is that the latter validates its inputs and the former
     does not.  Looking at the structure of the code, it would be easy to separate the validation
     from the creation using a simple Decorator pattern to wrap the creator factor with the
     validator factory.  This same concept could be further used to wrap the creator with a
     caching factory and then the validator.  The validator first validates the input, the caching
     factory checks to see if an instance with the given input parameters already exists, and if
     not, the creator factory creates one!  All with very clear roles and responsibilities.
   * I like the validation implementation in the Localizer more than the validation implementation
     for the "statement" or "document" subsystems.  I think it makes more sense since I get better
     code reuse and having all the validation in the factory instead of the constructors means
     that no memory allocation is incurred until after I know the data being used is valid.
   * The message subsystem unit tests look like acceptance tests.  Maybe you should move them to
     the correct location.
   * For LocalizerBundle, I am using an OrderedSet as the data structure.  I think that is wrong.
     I think what I want is for the LocalizerBundle to maintain the order in which they are added
     to the Localizer.  This would be different from OrderedSet which will order the
     LocalizerBundle instances according to their natural order.  Very different.

     
HERE:  
   * Rewrite test cases for exceptions to use a rule.  DONE.
   * After converting all the tests for exceptions to use a rule, double check by searching for
     the string "expected " to ensure you did not miss any.  DONE.
   * Then you should review again to ensure that ONLY the code that you want the exception from can throw the
     exception.


     
     
## Rough Notes

You should rename the "DocumentView" to "DocumentModel" because that is what it is.  When you start implementing
things like the "validation" subsystem, then the created validation data structures would be a view of the document
model, following the Model-View-Controller design pattern.  The same thing for the parser.

When implementing the copying of malicious, mutable data ensure you also handle those statements that contain complex
member data, such as VertexReferenceGroup containing VertexReferences.  You'll also need to handle defensive copying
for BigDecimal since it has an implementation flaw (missing "final" keyword in class definition) so it can be sub
classed and made modifiable.  NOTE: you have a prototype solution for testing BasisMatrix for copying, only.  Try
making the implementation as simple as possible so that you don't have to write too much code.  You'll have to test
both creating objects, data members that are non-final classes (BigDecimal) and data members that are classes defined
in this subsystem.
   * NOTE: You want to add additional testing to MutabilityTester to ensure the defensive copy ONLY occurs if the
     object being copied is NOT the expected type.

After completing the acceptance tests, check testing coverage to find where supplemental unit testing is required.

You should probably review and clean up the Javadoc for the published APIs.  For example, you should move the
discussion about the vertex references from the vertex implementations into the vertex reference implementations.

If you start using singleton, and fly weight patterns in the implementation, it would make more sense to do so
on a StatementFactory by StatementFactory basis, since eventually you want to be able to start setting different
policies changing behaviours when creating the StatementFactory.

It may make the interface simpler to use, and it is possible to enforce the minimum number of members
for statements with lists of things if you use varargs.  Actually, I'm not sure whether this idea will work
well with the parser.  I'll leave this idea until last.  NOTE: I started working on a solution for this in
the branch "refactor".  I'll keep it for now, but this work is no longer a priority.
