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
