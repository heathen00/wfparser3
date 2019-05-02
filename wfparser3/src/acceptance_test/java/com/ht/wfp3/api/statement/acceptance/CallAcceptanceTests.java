package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.fail;
import org.junit.Test;

public class CallAcceptanceTests {
  @Test
  public void Call_createCallWithNullFileNameParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithNullArgumentsListParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_copyCallWithNullCallParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithValidParametersAndOneIntegerArgument_validCallIsCreated() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithEmptyIntegerArgumentList_illegalArgumentExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithValidParametersAndNoParameterList_validCallIsCreated() {
    // TODO implementation note: you'll need to update the statement factory to allow creating a
    // Call with no arguments
    fail("Not yet implemented");
  }

  @Test
  public void Call_copyCallWithArgumentsList_validCallWithArgumentsListIsCopied() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_copyCallWithNoArgumentsList_validCallWithNoArgumentsIsCopied() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithFilenameParameterHasNoFileExtension_illegalArgumentExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithFilenameParameterHasUnsupportedFileExtension_illegalArgumentExceptionIsThrown() {
    // TODO You should publish the valid "obj" and "mod" file extensions in the Call interface.
    fail("Not yet implemented");
  }

  @Test
  public void Call_createCallWithValidParameters_validCallIsCreated() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_exerciseAllEqualsAndHashCodeVariants_equalsAndHashCodeContractIsRespected() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_exerciseAllCompareToVariants_compareToContractIsRespected() {
    fail("Not yet implemented");
  }

  @Test
  public void Call_copyMaliciousMutableCall_validImmutableCallIsCreated() {
    fail("Not yet implemented");
  }
}
