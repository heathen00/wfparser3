package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.ParamVertexReference;
import com.ht.wfp3.api.statement.SpecialPoint;
import com.ht.wfp3.api.statement.StatementFactory;

public class SpecialPointAcceptanceTests {
  private static final String SP_KEYWORD = "sp";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private ParamVertexReference createParamVertexReference(int vertexIndex) {
    return statementFactory.createParamVertexReference(Integer.valueOf(vertexIndex));
  }

  private void assertValidSpecialPoint(List<ParamVertexReference> vertexReferenceList,
      SpecialPoint specialPoint) {
    assertNotNull(specialPoint);
    assertEquals(SP_KEYWORD, specialPoint.getKeyword());
    assertEquals(vertexReferenceList, specialPoint.getVertexReferenceList());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void SpecialPoint_createSpecialPointWithNullVertexReferenceList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vertexReferenceList cannot be null");

    statementFactory.createSpecialPoint(null);
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingLessThanTheMinimumMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceList must contain at least ");

    statementFactory.createSpecialPoint(Collections.emptyList());
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vertexReferenceList cannot contain null members");

    statementFactory.createSpecialPoint(
        Arrays.asList(createParamVertexReference(1), null, createParamVertexReference(2)));
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingTheMinimumNumberOfMembers_specialPointIsCreated() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1));

    SpecialPoint specialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    assertValidSpecialPoint(vertexReferenceList, specialPoint);
  }

  @Test
  public void SpecialPoint_createSpecialPointWithVertexReferenceListContainingMoreThanTheMinimumNumberOfMembers_specialPointIsCreated() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1),
        createParamVertexReference(2), createParamVertexReference(3));

    SpecialPoint specialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    assertValidSpecialPoint(vertexReferenceList, specialPoint);
  }

  @Test
  public void SpecialPoint_copySpecialPointWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("sp cannot be null");

    statementFactory.copySpecialPoint(null);
  }

  @Test
  public void SpecialPoint_copySpecialPoint_specialPointIsCopied() {
    List<ParamVertexReference> vertexReferenceList = Arrays.asList(createParamVertexReference(1),
        createParamVertexReference(2), createParamVertexReference(3));
    SpecialPoint originalSpecialPoint = statementFactory.createSpecialPoint(vertexReferenceList);

    SpecialPoint copiedSpecialPoint = statementFactory.copySpecialPoint(originalSpecialPoint);

    assertValidSpecialPoint(vertexReferenceList, copiedSpecialPoint);
  }

  @Test
  public void SpecialPoint_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    SpecialPoint first;
    SpecialPoint second;

    first = statementFactory.createSpecialPoint(Arrays.asList(createParamVertexReference(33),
        createParamVertexReference(44), createParamVertexReference(55)));
    second = statementFactory.createSpecialPoint(Arrays.asList(createParamVertexReference(33),
        createParamVertexReference(44), createParamVertexReference(55)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different number of parameter vertex references
    first = statementFactory.createSpecialPoint(Arrays.asList(createParamVertexReference(33),
        createParamVertexReference(44), createParamVertexReference(55)));
    second = statementFactory.createSpecialPoint(
        Arrays.asList(createParamVertexReference(33), createParamVertexReference(44),
            createParamVertexReference(55), createParamVertexReference(66)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different parameter vertex reference values
    first = statementFactory.createSpecialPoint(Arrays.asList(createParamVertexReference(33),
        createParamVertexReference(44), createParamVertexReference(55)));
    second = statementFactory.createSpecialPoint(Arrays.asList(createParamVertexReference(33),
        createParamVertexReference(11), createParamVertexReference(55)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
