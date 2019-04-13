# WAVEFRONT PARSER VERSION 3


Rough list:
 * Fix API Definition: Replace non type safe String data members with type safe data members, as appropriate.
 * Fix API Definition: All Statement classes must be value objects, thus interfaces that have "append...()" type
   methods must be replaced with an implementation that does not allow the Statement to be modified once instantiated.
 * Fix API Definition: Should any statements that use a file name use File instead of just a String?  Probably.
 * Fix API Definition: Similar to "IsCommentable", should there be a common interface for all interfaces that use
   VertexReferenceGroups?  It would help ensure that all the interfaces uses the same method definitions, if appropriate.
 * Fix API Definition: Making everything type safe includes the StatementFactory as the StatementFactory should be at the
   same level of abstraction as the statements.  Handling of parsing string parameters for the statement objects should be
   handled elsewhere.  In some currently undefined subsystem of this solution (likely another package named "parser").
 * Fix API Definition: Similarly, the Builder type classes should AT LEAST support the same level of abstraction as the
   Statement objects, but may also support String objects as the builders are convenient and flexible and can be used
   across different levels of abstraction as a "bridge" for more complex statement types.  HOWEVER, classes like
   BigDecimal, Integer, etc., already provide facilities for conversions.  You should only provide your own APIs for
   conversion IF AND ONLY IF they provide some value such as handling conversion errors in a manner consistent with
   the rest of the system.
 * Fix API Definition: How to handle comments AND keep statements as value objects (i.e., unmodifiable??).
 * Fix API Definition: Ensure that VertexReference handling is consistent with things like CurvReference.
 * Automated Testing: Complete the Document view creation acceptance tests.
 * Automated Testing: Complete the StatementFactory creation acceptance tests.
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.


