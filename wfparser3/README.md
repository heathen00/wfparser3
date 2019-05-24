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

### Subsystems: Domain Objects

packages:
   * com.ht.wfp3.api.*


### Subsystems: Common Status Reporting

TODO: YOU NEED TO REVIEW AND CLEAN UP THIS SECTION.  THEN START WRITING ACCEPTANCE TESTS.

This software system consists of a number of subsystems.  These subsystems require a common mechanism for reporting
status messages while they perform their tasks in such a way that is not as disruptive as throwing an exception.
These status messages may not be reported immediately as they may delayed and presented in a final report document
after the subsystem completes its task.

There may be usage scenarios that require removing status messages.

Scenario: Remove status message:
   1. Initialize status of GeoVertex statement to "not used".
   1. Perform OBJ document validation task.
   1. Validation task determines GeoVertex statement is used.
   1. Remove "not used" status of GeoVertex statement.

The reported status messages will contain the following data:
   * An identifier unique to the status message
   * An identifier unique to the task.  The task is closely related to the subsystem, for example "parser" or
     "validation".  However, mapping directly to the subsystem is a bad idea because the subsystem (Java package) is
     an implementation artifact.  The task should also have a short name (maybe 10 characters or less) that supports
     i18n.
   * A priority: Not sure which priorities I'd like to have other than "NOT_DEFINED" as the default.  I would prefer
     not to hard code the priority values.  The priority itself should consist of a text name such as "DEBUG" but also
     an identifier unique to that priority.  The text name for the priority supports internationalization (i18n).  The
     priority name should also have a limited length, maybe 10 characters.
   * A human readable message: The human readable message is a short description of the event that occurred.  It
     supports i18n.  The length should be short enough that it fits the width of a standard UNIX terminal (80
     characters) minus the width of the priority name.  Perhaps a good starting maximum length is 50 characters.

The objective is to potentially support the task name, priority name, and status message on a single standard UNIX
terminal (80 characters at 12pts).  Thus, max lengths of:
   * task name: 10 characters
   * priority name: 10 characters
   * message: 50 characters
   * additional whitespace and formatting: 10 characters
   * overall line length (total of above): 80 characters

Since tasks, priorities, and status messages are defined during the reporting bootstrapping process, they can all
be checked to ensure they conform to the 

The status reporting system should bootstrap itself.  That is, when reading in the configuration for a set of status
messages for a given task (or tasks), then it should use itself to report any problems.  This implies that it has its
own set of status messages that it reads in first.  This further implies that there is a primordial "bootstrapping"
status message that it throws when bootstrapping itself.  That last status message (or possibly minimal set of status
messages) can be hard coded.

It may be necessary to link status messages (one or more messages for a given object) to an object belonging to a
separate subsystem.  See the "Remove status message" scenario.  On a related note, it may be necessary to support
parameterized strings in the status messages.

Scenario: Parsing OBJ file
   1. Parse OBJ file.
   1. Find GeoVertex that is incorrectly defined.
   1. Complete parsing.
   1. Report that "GeoVertex" statement at line number "XXX" is incorrectly defined because of specific reason.

In the above scenario, the strings in quotes would likely need to be parameterized.  Defining a different status
event for every statement may be too cumbersome.  What about "specific reason"?  To be more generic, you could
specify the character number, so it would be 'at character number "YYY"'.  At any rate, you'll likely want to
start with simple message reporting first, then enhance it with these features.

It was mentioned above that a report is generated.  This will be a simple model, and it will be the responsibility
of some as of yet undetermined subsystem(s) to format the messages according to those subsystems' requirements.
However, tools for activities like sorting based on priority/message/etc. should be provided.  At the very least,
a default natural ordering should be provided.

The "unique key" for both the priority and the full message should be private.  These would likely be generated
during the reporting system bootstrapping process and are not guaranteed to be unique across invocations.

The message "unique key" ONLY needs to be unique within the given task that it is defined.

The defined priorities should be defined across all tasks and event messages.

You may provide logging functionality as a simple event handler.

For testability and flexibility, you should implement the event handling, as well, an event handler where listeners
register to do "something" with the information reported to reporting subsystem.  Thus, you could then have a simple
logger that just logs the event immediately, or have a report creator that adds the events to a report that can
then be analyzed / reported after the task is complete.  Once you get the implementation refined enough, you could
consider providing an interface so that the subsystems that use the reporting subsystem could register their own
clients/listeners.


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
