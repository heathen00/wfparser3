package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.ObjectName;
import com.ht.wfp3.api.statement.StatementFactory;

public class ObjectNameAcceptanceTests {
  private static final String OBJECT_NAME_KEYWORD = "o";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidObjectName(String objectNameParameter, ObjectName objectName) {
    assertNotNull(objectName);
    assertEquals(OBJECT_NAME_KEYWORD, objectName.getKeyword());
    assertEquals(objectNameParameter, objectName.getObjectName());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void ObjectName_createObjectNameWithNullObjectName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("objectName cannot be null");

    statementFactory.createObjectName(null);
  }

  @Test
  public void ObjectName_createObjectNameWithEmptyObjectName_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("objectName can only contain ");

    statementFactory.createObjectName("");
  }

  @Test
  public void ObjectName_createObjectNameWithWhitespaceObjectName_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("objectName can only contain ");

    statementFactory.createObjectName("   \t ");
  }

  @Test
  public void ObjectName_createObjectNameContainingWhitespaceObjectName_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("objectName can only contain ");

    statementFactory.createObjectName("one two\tthree ");
  }

  @Test
  public void ObjectName_createObjectName_objectNameIsCreated() {
    String objectNameParameter = "square";

    ObjectName objectName = statementFactory.createObjectName(objectNameParameter);

    assertValidObjectName(objectNameParameter, objectName);
  }

  @Test
  public void ObjectName_copyObjectNameWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("o cannot be null");

    statementFactory.copyObjectName(null);
  }

  @Test
  public void ObjectName_copyObjectName_objectNameIsCopied() {
    String objectNameParameter = "dodecahedron";
    ObjectName originalObjectName = statementFactory.createObjectName(objectNameParameter);

    ObjectName copiedObjectName = statementFactory.copyObjectName(originalObjectName);

    assertValidObjectName(objectNameParameter, copiedObjectName);
  }

  @Test
  public void ObjectName_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    ObjectName first;
    ObjectName second;

    first = statementFactory.createObjectName("man_i_am_bad_at_naming_things");
    second = statementFactory.createObjectName("man_i_am_bad_at_naming_things");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // Not equal: different group name.
    first = statementFactory.createObjectName("man_i_am_bad_at_naming_things");
    second = statementFactory.createObjectName("walter");
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
