package com.ht.wfp3.message;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MessageFactoryTest {

  private MessageFactory messageFactory;

  @Before
  public void setup() {
    messageFactory = MessageSystem.createMessageSystem().createMessageFactory();
  }

  // TODO Set up the localization configuration, first, then come back to this and rewrite the tests
  // accounting for localization. In particular, the Priority "name" will NOT be specified as a
  // parameter.


  // TODO when adding messages to the MessageSystem, you'll have to check for duplicates. Actually,
  // you should probably let the factory manage the lists of priorities, topics, etc. So, this check
  // should be done here.

  // TODO you should research more detailed exception testing since in this case you need to ensure
  // The correct behaviour of the exception, too.
  @Test(expected = ConstraintViolationException.class)
  public void MessageFactory_createPriorityWithNullUidKey_constraintViolationExceptionIsThrown()
      throws Exception {
    messageFactory.createPriority(null, "TEST_PRIORITY");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithEmptyUidKey_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithUidKeyContainingWhitespaceOrNewLines_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithUidKeyIsTooLong_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithNullName_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithEmptyName_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithNameContainingNewlines_contraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_createPriorityWithNameIsTooLong_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("not implemented")
  public void MessageFactory_addPriorityWithDuplicateName_constraintViolationExceptionIsThrown() {
    fail("Not yet implemented");
  }
}
