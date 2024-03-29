package com.nickmlanglois.wfp3.api.statement.acceptance;

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
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.MapLib;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class MapLibAcceptanceTests {
  private static final String MAPLIB_KEYWORD = "maplib";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidMapLib(List<Path> mapLibFileNameList, MapLib mapLib) {
    assertNotNull(mapLib);
    assertEquals(MAPLIB_KEYWORD, mapLib.getKeyword());
    assertEquals(mapLibFileNameList, mapLib.getMapLibFileNameList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void MapLib_createMapLibWithNullMapLibFileNameList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("mapLibFileNameList cannot be null");

    statementFactory.createMapLib(null);
  }

  @Test
  public void MapLib_createMapLibWithEmptyMapLibFileNameList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapLibFileNameList must contain at least ");

    statementFactory.createMapLib(Collections.emptyList());
  }

  @Test
  public void MapLib_createMapLibWithMapLibFileNameListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("mapLibFileNameList cannot contain null members");

    statementFactory.createMapLib(
        Arrays.asList(Paths.get("home", "tex_map.lib"), null, Paths.get("foo", "bar.lib")));
  }

  @Test
  public void MapLib_createMapLibWithSingleMapLibFileNameInList_mapLibCreated() {
    List<Path> mapLibFileNameList = Arrays.asList(Paths.get("home", "texture_map.lib"));

    MapLib mapLib = statementFactory.createMapLib(mapLibFileNameList);

    assertValidMapLib(mapLibFileNameList, mapLib);
  }

  @Test
  public void MapLib_createMapLibWithMoreThanOneMapLIbFileNameInList_mapLibCreated() {
    List<Path> mapLibFileNameList = Arrays.asList(Paths.get("home", "texture_map.lib"),
        Paths.get("home", "yet_another_tex_map.lib"));

    MapLib mapLib = statementFactory.createMapLib(mapLibFileNameList);

    assertValidMapLib(mapLibFileNameList, mapLib);
  }

  @Test
  public void MapLib_copyMapLibWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("maplib cannot be null");

    statementFactory.copyMapLib(null);
  }

  @Test
  public void MapLib_copyMapLib_mapLibCopied() {
    List<Path> mapLibFileNameList = Arrays.asList(Paths.get("home", "texture_map.lib"),
        Paths.get("home", "yet_another_tex_map.lib"));
    MapLib originalMapLib = statementFactory.createMapLib(mapLibFileNameList);

    MapLib copiedMapLib = statementFactory.copyMapLib(originalMapLib);

    assertValidMapLib(mapLibFileNameList, copiedMapLib);
  }

  @Test
  public void MapLib_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    MapLib first;
    MapLib second;

    first = statementFactory
        .createMapLib(Arrays.asList(Paths.get("goat_map.lib"), Paths.get("moose_map.lib")));
    second = statementFactory
        .createMapLib(Arrays.asList(Paths.get("goat_map.lib"), Paths.get("moose_map.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of map file names.
    first = statementFactory.createMapLib(Arrays.asList(Paths.get("goat_map.lib"),
        Paths.get("moose_map.lib"), Paths.get("wombat_map.lib")));
    second = statementFactory
        .createMapLib(Arrays.asList(Paths.get("goat_map.lib"), Paths.get("moose_map.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different map files names.
    first = statementFactory.createMapLib(Arrays.asList(Paths.get("goat_map.lib"),
        Paths.get("moose_map.lib"), Paths.get("wombat_map.lib")));
    second = statementFactory.createMapLib(Arrays.asList(Paths.get("goat_map.lib"),
        Paths.get("moose_map.lib"), Paths.get("combat_map.lib")));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
  // TODO what about mapLibFileNameList with null members?
}
