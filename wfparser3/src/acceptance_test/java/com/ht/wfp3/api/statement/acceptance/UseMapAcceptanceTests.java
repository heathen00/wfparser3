package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.UseMap;

public class UseMapAcceptanceTests {

  private static final String USE_MAP_KEYWORD = "usemap";

  private StatementFactory statementFactory;

  private void assertValidUseMap(String mapName, boolean expectedIsEnabled, UseMap useMap) {
    assertNotNull(useMap);
    assertEquals(USE_MAP_KEYWORD, useMap.getKeyword());
    assertEquals(expectedIsEnabled, useMap.isEnabled());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void UseMap_createUseMapWithNullMapName_nullPointerExceptionIsThrown() {
    statementFactory.createUseMap(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void UseMap_createUseMapWithEmptyMapName_illegalArgumentExceptionIsThrown() {
    statementFactory.createUseMap("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void UseMap_createUseMapWithMapNameContainingWhitespace_illegalArgumentExceptinIsThrown() {
    statementFactory.createUseMap("one two c");
  }

  @Test(expected = IllegalArgumentException.class)
  public void UseMap_createUseMapWithMapNameContainingNewlines_illegalArgumentExceptionIsThrown() {
    statementFactory.createUseMap("firstLine\nsecondLine");
  }

  @Test(expected = IllegalArgumentException.class)
  public void UseMap_createUeMapWithMapNameContainingInvalidCharacters_illegalArgumentExceptionIsThrown() {
    statementFactory.createUseMap("!#%abc[]{}");
  }

  @Test
  public void UseMap_createUseMapWithMapNameContainingValidCharacters_useMapIsCreated() {
    String mapName = "valid_texture_map_name";
    boolean expectedIsEnabled = true;


    UseMap useMap = statementFactory.createUseMap(mapName);

    assertValidUseMap(mapName, expectedIsEnabled, useMap);
  }

  @Test
  public void UseMap_createUseMapWithMapNameEqualToOff_disabledUseMapIsCreated() {
    String mapName = "OfF";
    boolean expectedIsEnabled = false;

    UseMap useMap = statementFactory.createUseMap(mapName);

    assertValidUseMap(mapName, expectedIsEnabled, useMap);
  }

  @Test(expected = NullPointerException.class)
  public void UseMap_copyUseMapWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyUseMap(null);
  }

  @Test
  public void UseMap_copyUseMap_useMapCopied() {
    String mapName =
        "ANOTHER_valid_texture_map_name_though_the_name_is_quite_long_but_the_spec_does_not_say_anything_about_it_so_whatever";
    boolean expectedIsEnabled = true;
    UseMap originalUseMap = statementFactory.createUseMap(mapName);

    UseMap copiedUseMap = statementFactory.copyUseMap(originalUseMap);

    assertValidUseMap(mapName, expectedIsEnabled, copiedUseMap);
  }

  @Test
  public void UseMap_copyDisabledUseMap_disabledUseMapCopied() {
    String mapName = "off";
    boolean expectedIsEnabled = false;
    UseMap originalUseMap = statementFactory.createUseMap(mapName);

    UseMap copiedUseMap = statementFactory.copyUseMap(originalUseMap);

    assertValidUseMap(mapName, expectedIsEnabled, copiedUseMap);
  }

  @Test
  public void UseMap_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    UseMap first;
    UseMap second;

    first = statementFactory.createUseMap("mapname");
    second = statementFactory.createUseMap("mapname");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // different: different map name.
    first = statementFactory.createUseMap("mapname");
    second = statementFactory.createUseMap("another_mapname");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
