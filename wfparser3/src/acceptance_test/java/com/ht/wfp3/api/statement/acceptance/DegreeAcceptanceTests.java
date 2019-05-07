package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Degree;
import com.ht.wfp3.api.statement.StatementFactory;

public class DegreeAcceptanceTests {

  private static final String DEGREE_KEYWORD = "deg";
  private StatementFactory statementFactory;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Degree_createDegreeWithNullUAxisDegreeValue_nullPointerExceptionIsThrown() {
    statementFactory.createDegree(null, Integer.valueOf(3));
  }

  @Test(expected = NullPointerException.class)
  public void Degree_createDegreeWithNullVAxisDegreeValue_nullPointerExceptionIsThrown() {
    statementFactory.createDegree(Integer.valueOf(3), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Degree_createDegreeWithZeroUAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    statementFactory.createDegree(Integer.valueOf(0), Integer.valueOf(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Degree_createDegreeWithNegativeUAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    statementFactory.createDegree(Integer.valueOf(-1), Integer.valueOf(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Degree_createDegreeWithZeroVAxisDegreeValue_illegalArgumentExceptionIsThrown() {
    statementFactory.createDegree(Integer.valueOf(3), Integer.valueOf(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void Degree_createDegreeWithNegativeVAxisDegreeValue_illegalArgumentExceptionIsThrown() {
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

  @Test(expected = NullPointerException.class)
  public void Degree_copyDegreeWithNullParameter_nullPointerExceptionIsThrown() {
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

  @Test(expected = UnsupportedOperationException.class)
  public void Degree_accessVAxisDegreeWhenItWasNotSet_unsupportedOperationExceptionIsThrown() {
    Integer uAxisDegree = Integer.valueOf(3);
    Degree degree = statementFactory.createDegree(uAxisDegree);

    assertFalse(degree.isVAxisDegreeSet());
    degree.getVAxisDegree();
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
