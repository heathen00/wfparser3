package com.ht.localizer.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import com.ht.localizer.Localizer;
import com.ht.localizer.LocalizerInstance;
import com.ht.localizer.LocalizerType;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.uid.Uid;
import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

public class LocalizerInstanceAcceptanceTest {
  private TestableLocalizerFactory testableLocalizerFactory;
  private StubLocalizerFactory stubLocalizerFactory;
  private Localizer stubLocalizer;

  @Before
  public void setup() throws Exception {
    testableLocalizerFactory = TestableLocalizerFactory.getTestableLocalizerFactory(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    testableLocalizerFactory.resetAll();
    stubLocalizerFactory = StubLocalizerFactory.createStubLocalizerFactory();
    stubLocalizer = stubLocalizerFactory.createLocalizer("stub.localizer", Locale.CANADA_FRENCH);
  }

  @Test
  public void LocalizerInstance_createTestingAssets_testingAssetsCreated() {
    assertNotNull(testableLocalizerFactory);
    assertNotNull(stubLocalizerFactory);
  }

  @Test
  public void LocalizerInstance_createValidLocalizerInstanceThenGetUid_validLocalizerInstanceUidIsReturned()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedMethodName = "test.method";
    final String expectedInstanceName = "valid.instance.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedMethodName, expectedInstanceName);
    final LocalizerType expectedLocalizerType = stubLocalizerFactory.createLocalizerType(
        stubLocalizer, expectedGroupName, expectedTypeName, expectedMethodName);

    LocalizerInstance localizerInstance = testableLocalizerFactory
        .createLocalizerInstance(expectedLocalizerType, expectedInstanceName);
    assertNotNull(localizerInstance);
    Uid<LocalizerInstance> localizerInstanceUid = localizerInstance.getUid();

    assertNotNull(localizerInstanceUid);
    assertEquals(expectedFullyQualifiedName, localizerInstanceUid.getKey());
  }
}
