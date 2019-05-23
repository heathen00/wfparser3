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


## Rough Notes

When implementing the copying of malicious, mutable data ensure you also handle those statements that contain complex
member data, such as VertexReferenceGroup containing VertexReferences.  You'll also need to handle defensive copying
for BigDecimal since it has an implementation flaw (missing "final" keyword in class definition) so it can be sub
classed and made modifiable.  NOTE: you have a prototype solution for testing BasisMatrix for copying, only.  Try
making the implementation as simple as possible so that you don't have to write too much code.  You'll have to test
both creating objects, data members that are non-final classes (BigDecimal) and data members that are classes defined
in this subsystem.

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
