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
    * creation: validate parameters: null
    * creation: validate parameters: supported value ranges.
    * creation: validate parameters: optional parameters: How to handle?
    * equals and hashcode.
    * comparable?
    * defined methods: access to required parameters.
    * defined methods: access to optional parameters.

Notes:

It may make the interface simpler to use, and it is possible to enforce the minimum number of members
for statements with lists of things if you use varargs.

When implementing the copying of malicious, mutable ensure you also handle those statements that contain complex
member data, such as VertexReferenceGroup containing VertexReferences.

As a matter of consistency, should the VertexReferenceGroup be implemented similar to the ParamVertex where the
check for if the optional parameters is set is in the VertexReferenceGroup and NOT in the VertexReference?  Then,
similar to ParamVertex, you can throw an exception when trying to access data that is not set.

After completing the acceptance tests, check testing coverage to find where supplemental unit testing is required.

You should probably review and clean up the Javadoc for the published APIs.  For example, you should move the
discussion about the vertex references from the vertex implementations into the vertex reference implementations.

If you start using singleton, and fly weight patterns in the implementation, it would make more sense to do so
on a StatementFactory by StatementFactory basis, since eventually you want to be able to start setting different
policies changing behaviours when creating the StatementFactory.
