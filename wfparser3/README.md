# WAVEFRONT PARSER VERSION 3


Rough list:
 * Fix API Definition: After giving it some thought, it does not really make any sense for the Statement
   objects to be commented themselves.  The comments make sense in the context of the document, but they
   do not provide any additional semantic meaning to the statements that they comment from the perspective
   of the machine.  The comments are solely for the consumption of the human readers.  So, from that
   perspective, having comments as an attribute of statements makes no sense.  The comments are more of
   an aspect of the document than the OBJ statements.
 * Fix API Definition: For the Curve and Surface Approx classes, it might make more sense to have the approximation
   technique as a "fully owned" (I forget the OO term) class of the *Approx parent classes and also remove the type
   parameter as it would be expressed in the owned class.  This implementation would likely require a cast unless
   you implement a method for each sub type.  Keep it as error resistant as possible.
 * Automated Testing: Complete the Document view creation acceptance tests.
 * Automated Testing: Complete the StatementFactory creation acceptance tests.
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.  Are they unit tests?  Probably not.  Since
   The acceptance tests are defined by the user stories acceptance criteria for visible behaviour, and all published
   API classes are visible, then any behaviour that is associated with a published class is required to be defined
   by the acceptance tests, even if the user stories do not explicitly state the behaviour in their acceptance
   criteria.

