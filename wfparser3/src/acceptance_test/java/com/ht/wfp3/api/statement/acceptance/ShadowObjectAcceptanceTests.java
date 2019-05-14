package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.ShadowObject;
import com.ht.wfp3.api.statement.StatementFactory;

public class ShadowObjectAcceptanceTests {

  private static final String SHADOW_OBJ_KEYWORD = "shadow_obj";

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

  @Test(expected = NullPointerException.class)
  public void ShadowObject_createShadowObjectWithNullFileName_nullPointerExceptionIsThrown() {
    statementFactory.createShadowObject(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ShadowObject_createShadowObjectWithFileNameWithUnsupportedExtension_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void ShadowObject_copyShadowObjectWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyShadowObject(null);
  }

  @Test
  public void ShadowObject_copyShadowObject_shadowObjectIsCopied() {
    Path shadowObjectFileName = Paths.get("Downloads", "waffles.mod");
    ShadowObject originalShadowObject = statementFactory.createShadowObject(shadowObjectFileName);

    ShadowObject copiedShadowObject = statementFactory.copyShadowObject(originalShadowObject);

    assertValidShadowObject(shadowObjectFileName, copiedShadowObject);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
