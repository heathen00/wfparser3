package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.Degree;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.StatementFactory;

public class DegreeAcceptanceTests {
  private static final String DEGREE_KEYWORD = "deg";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Degree_createDegreeWithNullUAxisDegreeValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("uAxisDegree cannot be null");

    statementFactory.createDegree(null, Integer.valueOf(3));
  }

  @Test
  public void Degree_createDegreeWithNullVAxisDegreeValue_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("vAxisDegree cannot be null");

    statementFactory.createDegree(Integer.valueOf(3), null);
  }

  @Test
  public void Degree_createDegreeWithZeroUAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("uAxisDegree must be greater than ");

    statementFactory.createDegree(Integer.valueOf(0), Integer.valueOf(3));
  }

  @Test
  public void Degree_createDegreeWithNegativeUAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("uAxisDegree must be greater than ");

    statementFactory.createDegree(Integer.valueOf(-1), Integer.valueOf(3));
  }

  @Test
  public void Degree_createDegreeWithZeroVAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vAxisDegree must be greater than ");

    statementFactory.createDegree(Integer.valueOf(3), Integer.valueOf(0));
  }

  @Test
  public void Degree_createDegreeWithNegativeVAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("vAxisDegree must be greater than ");

    statementFactory.createDegree(Integer.valueOf(3), Integer.valueOf(-88));
  }

  @Test
  public void Degree_createDegreeWithValidUAndVAxisDegreeValues_validDegreeIsCreated() {
    Integer uAxisDegree = Integer.valueOf(3);
    Integer vAxisDegree = Integer.valueOf(11114);

    Degree degree = statementFactory.createDegree(uAxisDegree, vAxisDegree);

    assertNotNull(degree);
    assertEquals(DEGREE_KEYWORD, degree.getKeyword());
    assertEquals(uAxisDegree, degree.getUAxisDegree());
    assertEquals(vAxisDegree, degree.getVAxisDegree());
  }

  @Test
  public void Degree_createDegreeWithOnlyValidUAxisDegreeValue_validDegreeIsCreated() {
    Integer uAxisDegree = Integer.valueOf(54);

    Degree degree = statementFactory.createDegree(uAxisDegree);

    assertNotNull(degree);
    assertEquals(DEGREE_KEYWORD, degree.getKeyword());
    assertEquals(uAxisDegree, degree.getUAxisDegree());
  }

  @Test
  public void Degree_copyDegreeWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("deg cannot be null");

    statementFactory.copyDegree(null);
  }

  @Test
  public void Degree_copyValidDegreeWithOnlyUAxisDegreeValue_validDegreeIsCopied() {
    Integer uAxisDegree = Integer.valueOf(3);
    Integer vAxisDegree = Integer.valueOf(11114);
    Degree originalDegree = statementFactory.createDegree(uAxisDegree, vAxisDegree);

    Degree copiedDegree = statementFactory.copyDegree(originalDegree);

    assertNotNull(copiedDegree);
    assertEquals(DEGREE_KEYWORD, copiedDegree.getKeyword());
    assertEquals(uAxisDegree, copiedDegree.getUAxisDegree());
    assertEquals(vAxisDegree, copiedDegree.getVAxisDegree());
  }

  @Test
  public void Degree_copyValidDegreeWithUAndVAxisDegreeValues_validDegreeIsCopied() {
    Integer uAxisDegree = Integer.valueOf(3);
    Degree originalDegree = statementFactory.createDegree(uAxisDegree);

    Degree copiedDegree = statementFactory.copyDegree(originalDegree);

    assertNotNull(copiedDegree);
    assertEquals(DEGREE_KEYWORD, copiedDegree.getKeyword());
    assertEquals(uAxisDegree, copiedDegree.getUAxisDegree());
  }

  @Test
  public void Degree_accessVAxisDegreeWhenItWasNotSet_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("v axis degree is not set");

    Integer uAxisDegree = Integer.valueOf(3);
    Degree degree = statementFactory.createDegree(uAxisDegree);

    assertFalse(degree.isVAxisDegreeSet());
    degree.getVAxisDegree();
  }

  @Test
  public void Degree_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Degree first;
    Degree second;

    // Only u-axis degree specified.

    first = statementFactory.createDegree(Integer.valueOf(5));
    second = statementFactory.createDegree(Integer.valueOf(5));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal uAxisDegree different
    first = statementFactory.createDegree(Integer.valueOf(5));
    second = statementFactory.createDegree(Integer.valueOf(53));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    // u and v axis degrees specified.

    first = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(11));
    second = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(11));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: uAxisDegree different.
    first = statementFactory.createDegree(Integer.valueOf(100), Integer.valueOf(11));
    second = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(11));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: vAxisDegree different.
    first = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(11));
    second = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(111));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    // compare Degree with uAxisDegree only versus Degree with both uAxis and VAxis degrees set.
    first = statementFactory.createDegree(Integer.valueOf(10));
    second = statementFactory.createDegree(Integer.valueOf(10), Integer.valueOf(111));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);
  }

  // TODO copy malicious mutable statement.
}
