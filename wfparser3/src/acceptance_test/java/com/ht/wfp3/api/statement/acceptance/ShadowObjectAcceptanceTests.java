package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.ShadowObject;
import com.ht.wfp3.api.statement.StatementFactory;

public class ShadowObjectAcceptanceTests {
  private static final String SHADOW_OBJ_KEYWORD = "shadow_obj";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidShadowObject(Path shadowObjectFileName, ShadowObject shadowObject) {
    assertNotNull(shadowObject);
    assertEquals(SHADOW_OBJ_KEYWORD, shadowObject.getKeyword());
    assertEquals(shadowObjectFileName, shadowObject.getShadowObjectFileName());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void ShadowObject_createShadowObjectWithNullFileName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("shadowObjectFileName cannot be null");

    statementFactory.createShadowObject(null);
  }

  @Test
  public void ShadowObject_createShadowObjectWithFileNameWithUnsupportedExtension_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("shadowObjectFileName must have extension ");

    statementFactory.createShadowObject(Paths.get("home", "some_document.pdf"));
  }

  @Test
  public void ShadowObject_createShadowObjectWithFileNameWithObjExtension_shadowObjectIsCreated() {
    Path shadowObjectFileName = Paths.get("Downloads", "ShadowObject.obj");

    ShadowObject shadowObject = statementFactory.createShadowObject(shadowObjectFileName);

    assertValidShadowObject(shadowObjectFileName, shadowObject);
  }

  @Test
  public void ShadowObject_createShadowObjectWithFileNameWithModExtension_shadowObjectIsCreated() {
    Path shadowObjectFileName = Paths.get("Downloads", "waffles.mod");

    ShadowObject shadowObject = statementFactory.createShadowObject(shadowObjectFileName);

    assertValidShadowObject(shadowObjectFileName, shadowObject);
  }

  @Test
  public void ShadowObject_createShadowObjectWithFileNameWithNoExtension_shadowObjectIsCreated() {
    Path shadowObjectFileName = Paths.get("Downloads", "i_have_no_extension");
    Path expectedShadowObjectFileName = Paths.get("Downloads", "i_have_no_extension.obj");

    ShadowObject shadowObject = statementFactory.createShadowObject(shadowObjectFileName);

    assertValidShadowObject(expectedShadowObjectFileName, shadowObject);
  }

  @Test
  public void ShadowObject_copyShadowObjectWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("shadow_obj cannot be null");

    statementFactory.copyShadowObject(null);
  }

  @Test
  public void ShadowObject_copyShadowObject_shadowObjectIsCopied() {
    Path shadowObjectFileName = Paths.get("Downloads", "waffles.mod");
    ShadowObject originalShadowObject = statementFactory.createShadowObject(shadowObjectFileName);

    ShadowObject copiedShadowObject = statementFactory.copyShadowObject(originalShadowObject);

    assertValidShadowObject(shadowObjectFileName, copiedShadowObject);
  }

  @Test
  public void ShadowObject_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    ShadowObject first;
    ShadowObject second;

    first = statementFactory.createShadowObject(Paths.get("foo.mod"));
    second = statementFactory.createShadowObject(Paths.get("foo.mod"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different path names.
    first = statementFactory.createShadowObject(Paths.get("foo.mod"));
    second = statementFactory.createShadowObject(Paths.get("bar.mod"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
