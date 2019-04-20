# WAVEFRONT PARSER VERSION 3


Rough list:
 * Automated Testing: Review the completed view creation acceptance tests since some refactoring is required.
    * ensure all implementations have the correct visibility (package) at class and constructor levels.
    * review the implementation of the VertexReference / VertexReferenceGroup classes with regards
      to how the lists are handled since it is unnecessarily complex for immutable value objects.
    * you forgot the hashcode/equals/toString implementations for some statement/etc. classes.
    * review the names of all the statement classes.
    * review the method names in StatementFactory since you renamed some classes and their
      create/copy methods may still use the old name.
    * there is that one warning in the IDE about some local variable not being used.  Not a good sign.
    * deal with Blank and Unknown.  Maybe just move them into Document for now.  Ensure they are
      marked as not implemented.
    * deal with comments.  Ensure the statement factory does NOT refer to comments.  Add its handling
      to the Document subsystem.
    * The CurveApprox.Technique and SurfaceApprox.Technique should have a "keyword" attribute for their
      specific keywords, just like statements, i.e., "curv", "cparm", etc.
    * The CurveOrSurfaceType enum should also define their keywords like the approx techniques.
    * The Axis enum in Parm should also define its keywords.  If Axis is defined in multiple locations,
      consider defining it as a standalone enum instead of as contained within a parent class.
    * There are statement implementations that are currently using VertexReferenceGroups, but only actually
      support one vertex type:
        * Curv: Only supports geometric vertices.
        * SpecialPoint: Only supports parameter vertices.
    * Some statements, such as MerginGroup, have a "groupNumberOrOff" type of parameter.  In these
      cases, you should NOT specify enabled/disabled as a separate parameter to the constructor.  You
      should figure out internally in the constructor whether it is enabled or not.  The published
      API should still have a "boolean isEnabled()" method, though.  See the implementation for
      "SmoothingGroup" as an example for how to implement.  However, "UseMap" may be a better example.
    * Some class sub-hierarchies could probably get rid of their enums since unique classes exist for
      each subtype.  Maybe just add another "getTechniqueKeyword()" method.
 * Automated Testing: Complete the StatementFactory creation acceptance tests.
 * Automated Testing: Complete the Statement unit tests.  This might be difficult since you already have partial
   solutions in place due to defining the API which are likely incorrect since they have no unit tests associated
   with them.  Thus, there might be some re-implementation necessary.  Are they unit tests?  Probably not.  Since
   The acceptance tests are defined by the user stories acceptance criteria for visible behaviour, and all published
   API classes are visible, then any behaviour that is associated with a published class is required to be defined
   by the acceptance tests, even if the user stories do not explicitly state the behaviour in their acceptance
   criteria.
 * Design Error: If all statements are implemented as immutable value objects, what is the purpose in copying them
   when populating the document?  It only makes sense to do so IF the statements are mutable objects!  But right now,
   this is not causing any problems and having the ability to copy the statements may be useful.

