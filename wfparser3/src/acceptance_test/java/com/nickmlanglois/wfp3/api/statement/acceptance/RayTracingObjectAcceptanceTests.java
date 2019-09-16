package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.RayTracingObject;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class RayTracingObjectAcceptanceTests {
  private static final String TRACE_OBJ_KEYWORD = "trace_obj";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory = StatementFactory.createStatementFactory();


  private void assertValidRayTracingObject(Path expectedRayTracingObjectFileName,
      RayTracingObject rayTracingObject) {
    assertNotNull(rayTracingObject);
    assertEquals(TRACE_OBJ_KEYWORD, rayTracingObject.getKeyword());
    assertEquals(expectedRayTracingObjectFileName, rayTracingObject.getRayTracingObjectFileName());
  }

  @Test
  public void RayTracingObject_createRayTracingObjectWithNullFileName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("rayTracingObjectFileName cannot be null");

    statementFactory.createRayTracingObject(null);
  }

  @Test
  public void RayTracingObject_createRayTracingObjectWithFileNameWithUnsupportedExtension_invalidArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("rayTracingObjectFileName must have extension ");

    statementFactory.createRayTracingObject(Paths.get("home", "My Document.doc"));
  }

  @Test
  public void RayTracingObject_createRayTracingObjectWithFileNameWithNoExtension_rayTracingObjectIsCreated() {
    Path rayTracingObjectFileName = Paths.get("home", "some_file");
    Path expectedRayTracingObjectFileName = Paths.get("home", "some_file.obj");

    RayTracingObject rayTracingObject =
        statementFactory.createRayTracingObject(rayTracingObjectFileName);

    assertValidRayTracingObject(expectedRayTracingObjectFileName, rayTracingObject);
  }

  @Test
  public void RayTracingObject_createRayTracingObjectWithObjExtension_rayTracingObjectIsCreated() {
    Path rayTracingObjectFileName = Paths.get("home", "falcon_upper_cut.obj");

    RayTracingObject rayTracingObject =
        statementFactory.createRayTracingObject(rayTracingObjectFileName);

    assertValidRayTracingObject(rayTracingObjectFileName, rayTracingObject);
  }

  @Test
  public void RayTracingObject_createRayTracingObjectWithModExtension_rayTracingObjectIsCreated() {
    Path rayTracingObjectFileName = Paths.get("home", "vanilla_fudge.mod");

    RayTracingObject rayTracingObject =
        statementFactory.createRayTracingObject(rayTracingObjectFileName);

    assertValidRayTracingObject(rayTracingObjectFileName, rayTracingObject);
  }

  @Test
  public void RayTracingObject_copyRayTracingObjectWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("trace_obj cannot be null");

    statementFactory.copyRayTracingObject(null);
  }

  @Test
  public void RayTracingObject_copyRayTracingObject_rayTracingObjectIsCopied() {
    Path rayTracingObjectFileName = Paths.get("home", "some_file.mod");
    RayTracingObject originalRayTracingObject =
        statementFactory.createRayTracingObject(rayTracingObjectFileName);

    RayTracingObject copiedRayTracingObject =
        statementFactory.copyRayTracingObject(originalRayTracingObject);

    assertValidRayTracingObject(rayTracingObjectFileName, copiedRayTracingObject);
  }

  @Test
  public void RayTracingObject_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    RayTracingObject first;
    RayTracingObject second;

    first = statementFactory.createRayTracingObject(Paths.get("goat.obj"));
    second = statementFactory.createRayTracingObject(Paths.get("goat.obj"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different file names.
    first = statementFactory.createRayTracingObject(Paths.get("goat.obj"));
    second = statementFactory.createRayTracingObject(Paths.get("llama.obj"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
