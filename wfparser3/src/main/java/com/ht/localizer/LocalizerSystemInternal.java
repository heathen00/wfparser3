package com.ht.localizer;

import com.ht.uid.UidFactory;
import com.ht.wrap.WrapperFactory;

interface LocalizerSystemInternal extends LocalizerSystem {
  static LocalizerSystemInternal getSystemInternal() {
    return new LocalizerSystemInternalImp();
  }

  LocalizerFactoryInternal getLocalizerFactoryInternal();

  WrapperFactory createWrapperFactory();

  UidFactory createUidFactory();
}
