package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.UseMaterial;

public class UseMaterialAcceptanceTests {

  private static final String USEMTL_KEYWORD = "usemtl";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidUseMaterial(String materialName, UseMaterial useMaterial) {
    assertNotNull(useMaterial);
    assertEquals(USEMTL_KEYWORD, useMaterial.getKeyword());
    assertEquals(materialName, useMaterial.getMaterialName());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void UseMaterial_createUseMaterialWithNullMaterialName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("materialName cannot be null");

    statementFactory.createUseMaterial(null);
  }

  @Test
  public void UseMaterial_createUseMaterialWithEmptyMaterialName_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialName may only contain ");

    statementFactory.createUseMaterial("   ");
  }

  @Test
  public void UseMaterial_createUseMaterialWithMaterialNameContainingWhitespace_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialName may only contain ");

    statementFactory.createUseMaterial("this is illegal");
  }

  @Test
  public void UseMaterial_createUseMaterialWithMaterialNameContainingNewline_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialName may only contain ");

    statementFactory.createUseMaterial("this\nis\nalso\nillegal");
  }

  @Test
  public void UseMaterial_createUseMaterialWithMaterialNameContainingInvalidCharacters_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("materialName may only contain ");

    statementFactory.createUseMaterial("no$Invalid%Characters!");
  }

  @Test
  public void UseMaterial_createUseMaterialWithMaterialNameContainingValidCharacters_useMaterialIsCreated() {
    String materialName = "valid_Material_Name";

    UseMaterial useMaterial = statementFactory.createUseMaterial(materialName);

    assertValidUseMaterial(materialName, useMaterial);
  }

  @Test
  public void UseMaterial_copyUseMaterialWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("usemtl cannot be null");

    statementFactory.copyUseMaterial(null);
  }

  @Test
  public void UseMaterial_copyUseMaterial_useMaterialIsCopied() {
    String materialName = "valid_Material_Name";
    UseMaterial originalUseMaterial = statementFactory.createUseMaterial(materialName);

    UseMaterial copiedUseMaterial = statementFactory.copyUseMaterial(originalUseMaterial);

    assertValidUseMaterial(materialName, copiedUseMaterial);
  }

  @Test
  public void UseMaterial_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    UseMaterial first;
    UseMaterial second;

    first = statementFactory.createUseMaterial("some_material_name");
    second = statementFactory.createUseMaterial("some_material_name");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different material name
    first = statementFactory.createUseMaterial("some_material");
    second = statementFactory.createUseMaterial("some_material_name");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
