package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.GroupNameList;
import com.ht.wfp3.api.statement.StatementFactory;

public class GroupNameListAcceptanceTests {
  private static final Object GROUP_NAME_LIST_KEYWORD = "g";

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


  @Test(expected = NullPointerException.class)
  public void GroupNameList_createGroupNameListWithNullGroupNameListParameter_nullPointerExceptionIsThrown() {
    statementFactory.createGroupNameList(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void GroupNameLIst_createGroupNameListWithEmptyGroupNameList_illegalArgumentExceptionIsThrown() {
    statementFactory.createGroupNameList(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void GroupNameList_createGroupNameListWithNameListContainingNullMembers_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void GroupNameList_copyGroupNameListWithNullParameter_nullPointerExceptionIsThrown() {
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
