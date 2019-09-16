package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.Call;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester.Creator;

public class CallAcceptanceTests {
  private static final String CALL_KEYWORD = "call";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private StatementFactory statementFactory;

  private void assertValidCall(boolean isFrameNumberRequired, Path testPath,
      List<Integer> arguments, Call call) {
    assertNotNull(call);
    assertEquals(CALL_KEYWORD, call.getKeyword());
    assertEquals(isFrameNumberRequired, call.isFrameNumberRequired());
    assertEquals(testPath, call.getFileName());
    assertEquals(arguments, call.getArguments());
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
  }

  @Test
  public void Call_createCallWithNullFileNameParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("fileName cannot be null");

    statementFactory.createCall(false, null, new ArrayList<>());
  }

  @Test
  public void Call_createCallWithNullArgumentsListParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("arguments cannot be null");

    statementFactory.createCall(false, Paths.get("test.obj"), null);
  }

  @Test
  public void Call_copyCallWithNullCallParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("call cannot be null");

    statementFactory.copyCall(null);
  }

  @Test
  public void Call_createCallWithArgumentsListContainingNulls_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("arguments cannot contain null members");

    statementFactory.createCall(true, Paths.get("Downloads", "foo.obj"),
        Arrays.asList(Integer.valueOf(1), null, Integer.valueOf(45)));
  }

  @Test
  public void Call_createCallWithValidParametersAndOneIntegerArgument_validCallIsCreated() {
    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "test.mod");
    List<Integer> arguments = new ArrayList<>();
    arguments.add(Integer.valueOf(45));

    Call call = statementFactory.createCall(isFrameNumberRequired, testPath, arguments);

    assertValidCall(isFrameNumberRequired, testPath, arguments, call);
  }

  @Test
  public void Call_createCallWithEmptyIntegerArgumentList_validCallIsCreated() {
    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "test.obj");
    List<Integer> arguments = Collections.emptyList();

    Call call = statementFactory.createCall(isFrameNumberRequired, testPath, arguments);

    assertValidCall(isFrameNumberRequired, testPath, arguments, call);
  }

  @Test
  public void Call_copyCallWithArgumentsList_validCallWithArgumentsListIsCopied() {
    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "test.mod");
    List<Integer> arguments = new ArrayList<>();
    arguments.add(Integer.valueOf(45));
    arguments.add(Integer.valueOf(44));
    arguments.add(Integer.valueOf(24234));
    Call originalCall = statementFactory.createCall(isFrameNumberRequired, testPath, arguments);

    Call copiedCall = statementFactory.copyCall(originalCall);

    assertTrue(originalCall != copiedCall);
    assertValidCall(isFrameNumberRequired, testPath, arguments, copiedCall);
  }

  @Test
  public void Call_copyCallWithNoArgumentsList_validCallWithNoArgumentsIsCopied() {
    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "test.obj");
    List<Integer> arguments = Collections.emptyList();
    Call originalCall = statementFactory.createCall(isFrameNumberRequired, testPath, arguments);

    Call copiedCall = statementFactory.copyCall(originalCall);

    assertTrue(originalCall != copiedCall);
    assertValidCall(isFrameNumberRequired, testPath, arguments, copiedCall);
  }

  @Test
  public void Call_createCallWithFilenameParameterHasNoFileExtension_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("fileName must have extension ");

    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "this_file_has_no_extension");
    List<Integer> arguments = Collections.emptyList();

    statementFactory.createCall(isFrameNumberRequired, testPath, arguments);
  }

  @Test
  public void Call_createCallWithFilenameParameterHasUnsupportedFileExtension_illegalArgumentExceptionIsThrown() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("fileName must have extension ");

    boolean isFrameNumberRequired = false;
    Path testPath = Paths.get("home", "file_has_illegal_extension.doc");
    List<Integer> arguments = Collections.emptyList();

    statementFactory.createCall(isFrameNumberRequired, testPath, arguments);
  }

  @Test
  public void Call_exerciseAllEqualsHashCodeAndCompareToVariants_equalsHashCodeAndCompareToContractIsRespected() {
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    Call first;
    Call second;

    first = statementFactory.createCall(true, Paths.get("home", "test.mod"), Arrays.asList(34, 78));
    second =
        statementFactory.createCall(true, Paths.get("home", "test.mod"), Arrays.asList(34, 78));

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    // Not Equals: isFrameNumberRequired different
    first = statementFactory.createCall(true, Paths.get("home", "test.mod"), Arrays.asList(34, 78));
    second =
        statementFactory.createCall(false, Paths.get("home", "test.mod"), Arrays.asList(34, 78));

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not Equals: fileName different
    first = statementFactory.createCall(false, Paths.get("home", "AAA.mod"), Arrays.asList(34, 78));
    second =
        statementFactory.createCall(false, Paths.get("home", "ZZZ.mod"), Arrays.asList(34, 78));

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not Equals: arguments list different lengths
    first = statementFactory.createCall(false, Paths.get("home", "same.mod"),
        Arrays.asList(55, 56, 57));
    second = statementFactory.createCall(false, Paths.get("home", "same.mod"),
        Arrays.asList(55, 56, 57, 58));

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // Not equals: arguments values different.
    first =
        statementFactory.createCall(false, Paths.get("home", "same.mod"), Arrays.asList(55, 56));
    second =
        statementFactory.createCall(false, Paths.get("home", "same.mod"), Arrays.asList(28, 77));

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(false, first, second);

    // Not equals: null
    first =
        statementFactory.createCall(false, Paths.get("home", "test.obj"), Arrays.asList(56, 90));

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  @Test
  public void Call_checkMutableDefensiveCopy_validImmutableInstanceIsCreated() throws Exception {
    final boolean isFrameNumberRequired = false;
    final Path testPath = Paths.get("home", "test.obj");
    final List<Integer> arguments = Collections.unmodifiableList(
        Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)));
    final MutabilityTester<Call> mutabilityTester = new MutabilityTester<Call>(new Creator<Call>() {

      @Override
      public Call create() {
        return statementFactory.createCall(isFrameNumberRequired, testPath, arguments);
      }

      @Override
      public Call copy(Call o) {
        return statementFactory.copyCall(mutable(o));
      }

      @Override
      public Map<String, Object> getExpectedMemberData() {
        Map<String, Object> methodDataMap = new HashMap<>();
        methodDataMap.put("getArguments", arguments);
        return methodDataMap;
      }
    });
    mutabilityTester.assertImmutability();
  }
}
