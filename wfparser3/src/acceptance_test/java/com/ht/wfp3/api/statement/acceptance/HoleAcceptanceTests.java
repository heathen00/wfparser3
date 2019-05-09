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
import com.ht.wfp3.api.statement.Hole;
import com.ht.wfp3.api.statement.StatementFactory;

public class HoleAcceptanceTests {

  private static final Object HOLE_KEYWORD = "hole";

  private StatementFactory statementFactory;

  private void assertValidHole(List<Curve2DReference> curve2dReferenceList, Hole hole) {
    assertNotNull(hole);
    assertEquals(HOLE_KEYWORD, hole.getKeyword());
    assertEquals(curve2dReferenceList, hole.getCurve2DReferenceList());
  }

  private Curve2DReference createCurve2DReference(double startingParam, double endingParam,
      int curve2DIndex) {
    return statementFactory.createCurve2DReference(BigDecimal.valueOf(startingParam),
        BigDecimal.valueOf(endingParam), Integer.valueOf(curve2DIndex));
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test(expected = NullPointerException.class)
  public void Hole_createHoleWithNullCurve2DReferenceListParamter_nullPointerExceptionIsThrown() {
    statementFactory.createHole(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Hole_createHoleWithEmptyCurve2DReferenceList_illegalArgumentExceptionIsThrown() {
    statementFactory.createHole(Collections.emptyList());
  }

  @Test
  public void Hole_createHoleWithOneOrMoreCurve2DReferencesInCurve2DReferenceList_holeIsCreated() {
    List<Curve2DReference> curve2DReferenceList = Arrays
        .asList(createCurve2DReference(1.1d, 2.2d, 55), createCurve2DReference(6.7d, 55.123d, 88));

    Hole hole = statementFactory.createHole(curve2DReferenceList);

    assertValidHole(curve2DReferenceList, hole);
  }

  @Test(expected = NullPointerException.class)
  public void Hole_copyHoleWithNullParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyHole(null);
  }

  @Test
  public void Hole_copyHole_holeIsCopied() {
    List<Curve2DReference> curve2DReferenceList = Arrays
        .asList(createCurve2DReference(1.1d, 2.2d, 55), createCurve2DReference(6.7d, 55.123d, 88));
    Hole originalHole = statementFactory.createHole(curve2DReferenceList);

    Hole copiedHole = statementFactory.copyHole(originalHole);

    assertValidHole(curve2DReferenceList, copiedHole);
  }


  // TODO equals, hashCode, compareTo
  // TODO copy malicious mutable statement.
  // TODO what about when the curve2DReferenceList contains null members?

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
