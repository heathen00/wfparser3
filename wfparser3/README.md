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
   * Unique task identifier: The "task" identifies what task the software system is currently performing and is
        loosely associated with the subsystem that performs that task, such as "parser" or "validator".  The
        task will have a human readable name that supports internationalization (i18n).  It will have a positive
        non-zero integer that is unique across all tasks, thus the whole messaging subsystem.  The zeroth task
        is reserved for the standard "UNDEFINED" task for when a client erroneously attempts to use an unidentified
        task.  The first task is reserved for general "SYSTEM" errors for implementation problems that occurred in
        the reporting system that are not related to client problems.  The second task is also reserved for
        bootstrapping.  The task name and integer identifier are explicitly identified during the bootstrap process.
   * Message: The "message" is the event description. The message contains a human readable string that supports 
        i18n with a defined limited length.  The message string is optionally parameterized.  The message string is
        specified explicitly during the system bootstrap including parameterization tokens.
   * Unique message identifier: The message contains a positive non-zero integer that is unique within the task it
        is defined.  The zeroth message is reserved for the standard "UNDEFINED" message for when a client
        erroneously attempts to use an unidentified message.  The message unique identifier integer is specified
        explicitly during the system bootstraps process.
   * A priority: The priority will contain a human readable string name that supports i18n and has a system wide
        defined maximum string length.  The priority will have a unique non-zero integer identifier.  The zeroth
        priority is reserved for the "UNDEFINED" priority for when the client erroneously specifies a message
        with an unidentified priority.  The priorities are defined across all tasks and messages at subsystem
        bootstrap time and are unique across all tasks and messages.  The priorities are intended to be changeable
        for a given message, so should NOT be used as a part of uniquely identifying a message either within a task
        or across the entire message subsystem.        
   * Associated object?: The message is optionally associated with an object from another subsystem.  Alternative 1:
        The tracking is performed by the client system.  As long as the message properly implements equals, hashcode,
        and compareTo, then the tracking can be done in standard maps, lists, etc.  Alternative 2: Do not implement
        removing messages at all, and just assume that message reports will be regenerated if the system state
        changes.  You should delay message removal as long as possible to see if it is actually needed or not.  If
        it is, then move to "alternative 1".

All domain objects are implemented as immutable value objects.  The event handling would likely best be implemented
using a producer/consumer model.  Thus, if logging of events is desired, then a logging consumer could be
implemented that immediately logs events.  If a validation report generation is required, then the consumer would
generate the report by appending the messages to an appropriate, simple data structure.

It may be necessary to provide the ability to disable / enable events for scenarios like a given event was
incorrectly defined (or something) and should not be used anymore.


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
