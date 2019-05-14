# WAVEFRONT PARSER VERSION 3


Rough list:
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
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
    
I think my Matrix implementation is wrong.  It does not appear to create full rows with multiple values.  The
implementation will likely clear itself up when I write its acceptance tests.  It is a published interface,
after all.

If you start using singleton, and fly weight patterns in the implementation, it would make more sense to do so
on a StatementFactory by StatementFactory basis, since eventually you want to be able to start setting different
policies changing behaviours when creating the statementfactory.

Might need unit tests for classes like: StatementsUsingVertexReferenceGroupsImp.  See what remains after the
acceptance tests are done.

Need to test for when the *VertexReference classes are "not set".  This might require some code refactoring to
get it to work properly.

You should probably review and clean up the Javadoc for the published APIs.  For example, you should move the
discussion about the vertex references from the vertex implementations into the vertex reference implementations.

You may want to review the order for when checking for null members and checking the number of members for those
statements that contain lists of things.  You should probably check for null members first.

One of the first steps in the review would be to retest all statements and see if you broke anything since you've
only been testing individual statements at this point.  I'm sure I broke some tests.

