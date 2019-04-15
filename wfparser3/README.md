# WAVEFRONT PARSER VERSION 3


Rough list:
 * Fix API Definition: Making everything type safe includes the StatementFactory as the StatementFactory should be at the
   same level of abstraction as the statements.  Handling of parsing string parameters for the statement objects should be
   handled elsewhere.  In some currently undefined subsystem of this solution (likely another package named "parser").
 * Fix API Definition: Similarly, the Builder type classes should AT LEAST support the same level of abstraction as the
   Statement objects, but may also support String objects as the builders are convenient and flexible and can be used
   across different levels of abstraction as a "bridge" for more complex statement types.  HOWEVER, classes like
   BigDecimal, Integer, etc., already provide facilities for conversions.  You should only provide your own APIs for
   conversion IF AND ONLY IF they provide some value such as handling conversion errors in a manner consistent with
   the rest of the system.
 * Fix API Definition: Maybe for the MatrixBuilder, the "clear()" method should be replaced with a "start()" method
   that may or may not accept default configuration specifying how the Matrix will be built.
 * Fix API Definition: Similar to "IsCommentable", should there be a common interface for all interfaces that use
   VertexReferenceGroups?  It would help ensure that all the interfaces uses the same method definitions, if appropriate.
 * Fix API Definition: How to handle comments AND keep statements as value objects (i.e., unmodifiable??).
 * Fix API Definition: Ensure that VertexReference handling is consistent with things like CurvReference and Comment.
 * Automated Testing: Complete the Document view creation acceptance tests.
 * Automated Testing: Complete the StatementFactory creation acceptance tests.
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.  Are they unit tests?  Probably not.  Since
   The acceptance tests are defined by the user stories acceptance criteria for visible behaviour, and all published
   API classes are visible, then any behaviour that is associated with a published class is by required to be defined
   by the acceptance tests, even if the user stories do not explicitly state the behaviour.

