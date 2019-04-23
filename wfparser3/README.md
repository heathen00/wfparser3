# WAVEFRONT PARSER VERSION 3


Rough list:
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.  Are they unit tests?  Probably not.  Since
   The acceptance tests are defined by the user stories acceptance criteria for visible behaviour, and all published
   API classes are visible, then any behaviour that is associated with a published class is required to be defined
   by the acceptance tests, even if the user stories do not explicitly state the behaviour in their acceptance
   criteria.  How will you deal with optional data members?  It seems to be a common problem for many statements.
 * Design Error: If all statements are implemented as immutable value objects, what is the purpose in copying them
   when populating the document?  It only makes sense to do so IF the statements are mutable objects!  But right now,
   this is not causing any problems and having the ability to copy the statements may be useful.

