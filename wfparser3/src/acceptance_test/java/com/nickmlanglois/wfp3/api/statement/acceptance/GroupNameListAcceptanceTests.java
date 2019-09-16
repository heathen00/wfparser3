package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.GroupNameList;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;

public class GroupNameListAcceptanceTests {
  private static final Object GROUP_NAME_LIST_KEYWORD = "g";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidGroupNameList(List<String> groupNameListParameter,
      GroupNameList groupNameList) {
    assertNotNull(groupNameList);
    assertEquals(GROUP_NAME_LIST_KEYWORD, groupNameList.getKeyword());
    assertEquals(groupNameListParameter, groupNameList.getGroupNameList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }


  @Test
  public void GroupNameList_createGroupNameListWithNullGroupNameListParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("groupNameList cannot be null");

    statementFactory.createGroupNameList(null);
  }

  @Test
  public void GroupNameLIst_createGroupNameListWithEmptyGroupNameList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("groupNameList must contain at least ");

    statementFactory.createGroupNameList(Collections.emptyList());
  }

  @Test
  public void GroupNameList_createGroupNameListWithNameListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("groupNameList cannot contain null members");

    statementFactory.createGroupNameList(Arrays.asList("group_00", null, "group_02"));
  }

  @Test
  public void GroupNameList_createGroupNameListWithOneGroupNameInGroupNameList_groupNameListCreated() {
    List<String> groupNameListParameter = Arrays.asList("test_group");

    GroupNameList groupNameList = statementFactory.createGroupNameList(groupNameListParameter);

    assertValidGroupNameList(groupNameListParameter, groupNameList);
  }

  @Test
  public void GroupNameList_createGroupNameListWithMoreThanOneGroupNameInGroupNameList_groupNameListCreated() {
    List<String> groupNameListParameter =
        Arrays.asList("test_group_00", "test_group_01", "test_group_02");

    GroupNameList groupNameList = statementFactory.createGroupNameList(groupNameListParameter);

    assertValidGroupNameList(groupNameListParameter, groupNameList);
  }

  @Test
  public void GroupNameList_copyGroupNameListWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("g cannot be null");

    statementFactory.copyGroupNameList(null);
  }

  @Test
  public void GroupNameList_copyGroupNameList_groupNameListIsCopied() {
    List<String> groupNameListParameter =
        Arrays.asList("test_group_00", "test_group_01", "test_group_02");
    GroupNameList originalGroupNameList =
        statementFactory.createGroupNameList(groupNameListParameter);

    GroupNameList copiedGroupNameList = statementFactory.copyGroupNameList(originalGroupNameList);

    assertValidGroupNameList(groupNameListParameter, copiedGroupNameList);
  }

  @Test
  public void GroupNameList_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    GroupNameList first;
    GroupNameList second;

    first = statementFactory.createGroupNameList(Arrays.asList("group_00", "group_01", "ralf"));
    second = statementFactory.createGroupNameList(Arrays.asList("group_00", "group_01", "ralf"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of group names.
    first = statementFactory.createGroupNameList(Arrays.asList("group_00", "group_01", "ralf"));
    second = statementFactory
        .createGroupNameList(Arrays.asList("group_00", "group_01", "ralf", "cantaloup"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different group names.
    first = statementFactory.createGroupNameList(Arrays.asList("group_00", "lake", "ralf"));
    second = statementFactory.createGroupNameList(Arrays.asList("group_00", "group_01", "ralf"));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
  // TODO what happens if you specify the same group name twice?
}
