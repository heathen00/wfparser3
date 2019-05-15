package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Curve2DReference;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.Trim;

public class TrimAcceptanceTests {

  private static final String TRIM_KEYWORD = "trim";

  private StatementFactory statementFactory;

  private void assertValidTrim(List<Curve2DReference> curve2dReferenceList, Trim trim) {
    assertNotNull(trim);
    assertEquals(TRIM_KEYWORD, trim.getKeyword());
    assertEquals(Trim.MINIMUM_CURVE2D_REFERENCES, trim.getMinimumNumberOfCurve2DReferences());
    assertEquals(curve2dReferenceList, trim.getCurve2DReferenceList());
  }

  private Curve2DReference createCurve2DReference(double startParameter, double endParameter,
      int curveIndex) {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(startParameter),
        BigDecimal.valueOf(endParameter), Integer.valueOf(curveIndex));
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Trim_createTrimWithNullCurve2DReferenceList_nullPointerExceptionIsThrown() {
    statementFactory.createTrim(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Trim_createTrimWithCurve2DReferenceListWithLessThanMinimumMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createTrim(Collections.emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Trim_createTrimWithCurve2DReferenceListsContainingNullMembers_illegalArgumentExceptionIsThrown() {
    statementFactory.createTrim(Arrays.asList(createCurve2DReference(1.1d, 2.2d, 1), null,
        createCurve2DReference(3.3d, 4.4d, 2)));
  }

  @Test
  public void Trim_createTrimWithCurve2DReferenceListContainingMinimumNumberOfMembers_trimIsCreated() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(createCurve2DReference(1.1d, 2.2d, 1));

    Trim trim = statementFactory.createTrim(curve2DReferenceList);

    assertValidTrim(curve2DReferenceList, trim);
  }

  @Test
  public void Trim_createTrimWithCurve2DReferenceListContainingMoreThanTheMinimumNumberOfMembers_trimIsCreated() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(createCurve2DReference(1.1d, 2.2d, 1), createCurve2DReference(3.3d, 4.4d, 2),
            createCurve2DReference(5.5d, 6.6d, 3));

    Trim trim = statementFactory.createTrim(curve2DReferenceList);

    assertValidTrim(curve2DReferenceList, trim);
  }

  @Test(expected = NullPointerException.class)
  public void Trim_copyTrimWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyTrim(null);
  }

  @Test
  public void Trim_copyTrim_trimIsCopied() {
    List<Curve2DReference> curve2DReferenceList =
        Arrays.asList(createCurve2DReference(1.1d, 2.2d, 1), createCurve2DReference(3.3d, 4.4d, 2),
            createCurve2DReference(5.5d, 6.6d, 3));
    Trim originalTrim = statementFactory.createTrim(curve2DReferenceList);

    Trim copiedTrim = statementFactory.copyTrim(originalTrim);

    assertValidTrim(curve2DReferenceList, copiedTrim);
  }

  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
