# WAVEFRONT PARSER VERSION 3


Rough list:
 * Automated Testing: Complete the Statement acceptance tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.  Are they unit tests?  Probably not.  Since
   The acceptance tests are defined by the user stories acceptance criteria for visible behaviour, and all published
   API classes are visible, then any behaviour that is associated with a published class is required to be defined
   by the acceptance tests, even if the user stories do not explicitly state the behaviour in their acceptance
   criteria.  How will you deal with optional data members?  It seems to be a common problem for many statements.
   To consider:
    * equals and hashcode.
    * comparable?

Notes:

You should refactor how you've defined how Statement extends Comparable.  Try something like <T extends Statement>
instead of <Statement>.  Then perhaps the subclasses could implement Comparable<SubClassOfStatement> or something.
If it works, do the same for VertexReference and its subclasses.

The Surface statement's compareTo is, wrong in the sense that unlike all the other statements, its compareTo is
NOT sorting according to the order of its parameters.  This is because the evaluation of the List<VertexReferenceGroup>
data member is performed by the super class, at the beginning of the Surface compareTo method.  The correct solution
to fix this would be to replace the StatementsUsingVertexReferenceGroupsImp superclass with a package visible class
that implements the functionality it contains and replace the inheritance with composition.  For consistency, you
should do this for all Statement classes that are subclasses of StatementsUsingVertexReferenceGroupsImp.

When implementing the copying of malicious, mutable ensure you also handle those statements that contain complex
member data, such as VertexReferenceGroup containing VertexReferences.

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
