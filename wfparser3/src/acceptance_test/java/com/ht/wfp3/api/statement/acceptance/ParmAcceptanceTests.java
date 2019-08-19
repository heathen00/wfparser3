package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.ht.wfp3.api.statement.Axis;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.ht.wfp3.api.statement.Parm;
import com.ht.wfp3.api.statement.StatementFactory;

public class ParmAcceptanceTests {
  private static final String PARM_KEYWORD = "parm";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidParm(Axis axis, List<BigDecimal> parameterList, Parm parm) {
    assertNotNull(parm);
    assertEquals(PARM_KEYWORD, parm.getKeyword());
    assertEquals(axis, parm.getAxis());
    assertEquals(parameterList, parm.getParameterValues());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Parm_createParmWithNullAxis_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("axis cannot be null");

    statementFactory.createParm(null,
        Arrays.asList(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d)));
  }

  @Test
  public void Parm_createParmWithNullParameterList_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("parameterList cannot be null");

    statementFactory.createParm(Axis.U, null);
  }

  @Test
  public void Parm_createParmWithEmptyParameterList_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("parameterList must contain at least ");

    statementFactory.createParm(Axis.V, Collections.emptyList());
  }

  @Test
  public void Parm_createParmWithParameterListContainingNullMembers_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("parameterList cannot contain null members");

    statementFactory.createParm(Axis.U,
        Arrays.asList(BigDecimal.valueOf(1.1d), null, BigDecimal.valueOf(3d)));
  }

  @Test
  public void Parm_createParmWithLessThanTwoParametersInParameterList_illegalArgumentExceptionIsthrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("parameterList must contain at least ");

    statementFactory.createParm(Axis.V, Arrays.asList(BigDecimal.valueOf(1.1d)));
  }

  @Test
  public void Parm_createParmWithTwoParametersInParameterList_parmIsCreated() {
    Axis axis = Axis.U;
    List<BigDecimal> parameterList =
        Arrays.asList(BigDecimal.valueOf(1.1d), BigDecimal.valueOf(2.2d));

    Parm parm = statementFactory.createParm(axis, parameterList);

    assertValidParm(axis, parameterList, parm);
  }

  @Test
  public void Parm_createParmWithMoreThanTwoParametersInParameterList_parmIsCreated() {
    Axis axis = Axis.V;
    List<BigDecimal> parameterList = Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d));

    Parm parm = statementFactory.createParm(axis, parameterList);

    assertValidParm(axis, parameterList, parm);
  }

  @Test
  public void Parm_copyParmWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("parm cannot be null");

    statementFactory.copyParm(null);
  }

  @Test
  public void Parm_copyParm_parmIsCopied() {
    Axis axis = Axis.V;
    List<BigDecimal> parameterList = Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d), BigDecimal.valueOf(4.4d));
    Parm originalParm = statementFactory.createParm(axis, parameterList);

    Parm copiedParm = statementFactory.copyParm(originalParm);

    assertValidParm(axis, parameterList, copiedParm);
  }

  @Test
  public void Parm_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Parm first;
    Parm second;

    first = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    second = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    // not equal: different axes
    first = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    second = statementFactory.createParm(Axis.V, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // not equal: different number of parameters
    first = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d), BigDecimal.valueOf(-4.4d)));
    second = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // not equal: different parameter values
    first = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(-3.3d)));
    second = statementFactory.createParm(Axis.U, Arrays.asList(BigDecimal.valueOf(1.1d),
        BigDecimal.valueOf(2.2d), BigDecimal.valueOf(3.3d)));
    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);
  }

  // TODO copy malicious mutable statement.
}
