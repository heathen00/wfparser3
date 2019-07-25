package com.ht.l10n;

import com.ht.wrap.WrapperFactory;

interface ConfigurableWrapperFactory {
  void setWrapperFactory(WrapperFactory wrapperFactory);

  WrapperFactory getWrapperFactory();
}
