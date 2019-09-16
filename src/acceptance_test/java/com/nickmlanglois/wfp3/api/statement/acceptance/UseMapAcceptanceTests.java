package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.UseMap;

public class UseMapAcceptanceTests {

  private static final String USE_MAP_KEYWORD = "usemap";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void UseMap_createUseMapWithNullMapName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mapNameOrOff cannot be null");

    statementFactory.createUseMap(null);
  }

  @Test
  public void UseMap_createUseMapWithEmptyMapName_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapNameOrOff may only contain ");

    statementFactory.createUseMap("");
  }

  @Test
  public void UseMap_createUseMapWithMapNameContainingWhitespace_illegalArgumentExceptinIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapNameOrOff may only contain ");

    statementFactory.createUseMap("one two c");
  }

  @Test
  public void UseMap_createUseMapWithMapNameContainingNewlines_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapNameOrOff may only contain ");

    statementFactory.createUseMap("firstLine\nsecondLine");
  }

  @Test
  public void UseMap_createUeMapWithMapNameContainingInvalidCharacters_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapNameOrOff may only contain ");

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

  @Test
  public void UseMap_copyUseMapWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("usemap cannot be null");

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
}
