package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.MaterialLib;
import com.ht.wfp3.api.statement.StatementFactory;

public class MaterialLibAcceptanceTests {
  private static final String MTLLIB_KEYWORD = "mtllib";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidMaterialLib(List<Path> materialLibFileNameList, MaterialLib materialLib) {
    assertNotNull(materialLib);
    assertEquals(MTLLIB_KEYWORD, materialLib.getKeyword());
    assertEquals(materialLibFileNameList, materialLib.getMaterialLibFileNameList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void MaterialLib_createMaterialLibWithNullMaterialLibFileNameList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("materialLibFileNameList cannot be null");

    statementFactory.createMaterialLib(null);
  }

  @Test
  public void MaterialLib_createMaterialLibWithEmptyMaterialLibFileNameList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialLibFileNameList must contain at least ");

    statementFactory.createMaterialLib(Collections.emptyList());
  }

  @Test
  public void MaterialLib_createMaterialLibWithMaterialLibFileNameListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialLibFileNameList cannot contain null members");

    statementFactory.createMaterialLib(
        Arrays.asList(Paths.get("home", "foo.mtl"), null, Paths.get("home", "bar.mtl")));
  }

  @Test
  public void MaterialLib_createMaterialLibWithOneMaterialLibFileNameInList_materialLibIsCreated() {
    List<Path> materialLibFileNameList = Arrays.asList(Paths.get("home", "foo.mtl"));

    MaterialLib materialLib = statementFactory.createMaterialLib(materialLibFileNameList);

    assertValidMaterialLib(materialLibFileNameList, materialLib);
  }

  @Test
  public void MaterialLib_createMaterialLibWithMoreThanOneMaterialLibFileNameInLIst_materialLibIsCreated() {
    List<Path> materialLibFileNameList =
        Arrays.asList(Paths.get("home", "foo.mtl"), Paths.get("home", "bar.mtl"));

    MaterialLib materialLib = statementFactory.createMaterialLib(materialLibFileNameList);

    assertValidMaterialLib(materialLibFileNameList, materialLib);
  }

  @Test
  public void MaterialLib_copyMaterialLibWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mtllib cannot be null");

    statementFactory.copyMaterialLib(null);
  }

  @Test
  public void MaterialLib_copyMaterialLib_materialLibCopied() {
    List<Path> materialLibFileNameList =
        Arrays.asList(Paths.get("home", "foo.mtl"), Paths.get("home", "bar.mlt"));
    MaterialLib originalMaterialLib = statementFactory.createMaterialLib(materialLibFileNameList);

    MaterialLib copiedMaterialLib = statementFactory.copyMaterialLib(originalMaterialLib);

    assertValidMaterialLib(materialLibFileNameList, copiedMaterialLib);
  }

  @Test
  public void MaterialLib_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    MaterialLib first;
    MaterialLib second;

    first = statementFactory
        .createMaterialLib(Arrays.asList(Paths.get("foo_mat.lib"), Paths.get("bar_mat.lib")));
    second = statementFactory
        .createMaterialLib(Arrays.asList(Paths.get("foo_mat.lib"), Paths.get("bar_mat.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of material file names
    first = statementFactory
        .createMaterialLib(Arrays.asList(Paths.get("foo_mat.lib"), Paths.get("bar_mat.lib")));
    second = statementFactory.createMaterialLib(Arrays.asList(Paths.get("foo_mat.lib"),
        Paths.get("bar_mat.lib"), Paths.get("bar_mat.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different material file names
    first = statementFactory
        .createMaterialLib(Arrays.asList(Paths.get("foo_mat.lib"), Paths.get("bar_mat.lib")));
    second = statementFactory
        .createMaterialLib(Arrays.asList(Paths.get("fool_mat.lib"), Paths.get("bar_mat.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
