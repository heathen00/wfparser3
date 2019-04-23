# WAVEFRONT PARSER VERSION 3


Rough list:
 * Automated Testing: Review the completed view creation acceptance tests since some refactoring is required.
    * There are statement implementations that are currently using VertexReferenceGroups, but only actually
      support one vertex type.  A related issue is whether ANY geometric objects use the parameter vertex
      in a vertex group with the other vertex types.  If not, you should remove it from the vertex reference
      group:
        * Curv: Only supports geometric vertices.
        * SpecialPoint: Only supports parameter vertices.
        * Check for others.
    * review the implementation of the VertexReference / VertexReferenceGroup classes with regards
      to how the lists are handled since it is unnecessarily complex for immutable value objects.
    * some interfaces are not intended to be published.  Should you rename them with the "Imp" suffix
      to reflect that?
    * review the names of all the statement classes.
    * review the method names in StatementFactory since you renamed some classes and their
      create/copy methods may still use the old name.
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

