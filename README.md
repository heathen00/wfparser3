# WAVEFRONT PARSER


## Introduction

A parser for the Wavefront OBJ and MTL file formats.


## Build and IDE Integration

This project uses Gradle and follows standard Gradle conventions. I use Eclipse with the Buildship Gradle plugin. Importing this project using Buildship's "Import existing Gradle project" sets up the project with no additional work.


## Testing

All testing is automated and divided into two categories, "unit tests" and "acceptance tests". All unit tests and testing utilities are in the "src/test/" folder. All acceptance tests, that is automated tests that prove the project implements the user stories' acceptance criteria, are in the "src/acceptance_test/" folder. An easy rule of thumb to make a distinction between these two sets of automated tests is that the acceptance tests test the externally visible or "published" behaviour of the software system, whereas the unit tests focus on testing internal or "implementation" behaviour of the software system. I tend to favour acceptance tests over unit tests so if you run test coverage reports you'll likely find that testing coverage is not ideal.

If you are looking for specific client use cases, you will see them in the acceptance tests.


## Rough Notes

You should rename the "DocumentView" to "DocumentModel" because that is what it is.  When you start implementing
things like the "validation" subsystem, then the created validation data structures would be a view of the document
model, following the Model-View-Controller design pattern.  The same thing for the parser.

When implementing the copying of malicious, mutable data ensure you also handle those statements that contain complex
member data, such as VertexReferenceGroup containing VertexReferences.  You'll also need to handle defensive copying
for BigDecimal since it has an implementation flaw (missing "final" keyword in class definition) so it can be sub
classed and made modifiable.  NOTE: you have a prototype solution for testing BasisMatrix for copying, only.  Try
making the implementation as simple as possible so that you don't have to write too much code.  You'll have to test
both creating objects, data members that are non-final classes (BigDecimal) and data members that are classes defined
in this subsystem.
   * NOTE: You want to add additional testing to MutabilityTester to ensure the defensive copy ONLY occurs if the
     object being copied is NOT the expected type.

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


## START: OLD


Create the following projects in GitHub for potential future projects to work on?
     

### Subsystems: The Guard And Constraint Subsystem
  
Rough description
   * I want to create another small subsystem in a package called "com.nickmlanglois.guard" that provides
     reusable guard code that can be used throughout all other subsystems, but I am having a
     difficult time figuring out how to structure the code so that the guard code supports optionally
     throwing exceptions, especially checked exceptions, without explicitly adding a generic
     "throws Exception" to the method interface.  I probably need to implement two interfaces within
     the subsystem.  One to mark a parameter to check for a specific condition.  Another to perform
     a specific action when that condition occurs.  The former method would never need to specify
     a throws clause.  The latter method would be implementation specific and might specify a
     throws clause.  I'm not sure that gets me any further ahead ...  Leave this until later.  If
     you do write this, make it a whitelist instead of a blacklist.  Also, you should set the
     naming convention you want to specify as compatible with Java package, class, and data field
     names for group, type, and field names respectively.  This would permit you to potentially
     defined annotations that automatically determined most of the data.  The only thing not set
     is the instance name which would be set in the annotation.  You should look at the Java
     validation framework.  It is an annotation based solution that you could potentially
     base your solution on.  At least some of the default annotations can be applied to
     method parameters.  Would be interesting to see if these or annotations of your own
     creation could be used.
   * The message subsystem contains "Constraint" and "ConstraintViolation" implementations that
     really don't belong in the message subsystem.  This is a similar concept to the "guard"
     subsystem I've mentioned previously and maybe should be rolled into that.  I'll leave it for
     now since I'm busy replacing the localization implementation at the moment but maybe after
     that the next step is to build the guard / constraints solution.  The constraints are things
     like the length of UIDs, length of descriptions, naming conventions, ...
   * I like the validation implementation in the Localizer more than the validation implementation
     for the "statement" or "document" subsystems.  I think it makes more sense since I get better
     code reuse and having all the validation in the factory instead of the constructors means
     that no memory allocation is incurred until after I know the data being used is valid.
   * NOTE: While writing the StubUidFactory, it occurred to me that the primary difference between
     the StubUidFactory and the UidFactory is that the latter validates its inputs and the former
     does not.  Looking at the structure of the code, it would be easy to separate the validation
     from the creation using a simple Decorator pattern to wrap the creator factor with the
     validator factory.  This same concept could be further used to wrap the creator with a
     caching factory and then the validator.  The validator first validates the input, the caching
     factory checks to see if an instance with the given input parameters already exists, and if
     not, the creator factory creates one!  All with very clear roles and responsibilities.


### Subsystems: The Connect Subsystem

Rough:
  * very poorly defined at the moment.
  * the idea is to have similar interfaces between subsystems to simplify integrating them.  The problem is that
    I don't have a good idea how to do this yet.  So, thus far, I am just implementing similar interfaces between
    subsystems to see if anything can be done to make them consistent enough.
  * All the subsystem interconnection logic MUST (by definition practically) be defined outside those subsystems.
    You'd have one subsystem that defines the connections points and the connections in a generic way.  Possibly
    only interfaces.  Then for the overall software system, an "integration" subsystem that implements all the
    connection points and connections.  The connection points and connections would define the services that
    each subsystem is required to provide.
  * The connection subsystem would be like the "wrap" subsystem.  In fact, the "wrap" subsystem should just
    implement the appropriate connection interfaces.
  * Also, continue to define very similar interfaces at a subsystem level.  Eventually, I would
    like to implement what I intended to implement in the "connect" subsystem.  It is just too
    soon, right now, because I don't see the requirements, yet.

