package com.ht.localizer;

import com.ht.wrap.WrapperFactory;

interface ConfigurableWrapperFactory {
  void setWrapperFactory(WrapperFactory wrapperFactory);

  WrapperFactory getWrapperFactory();
}
