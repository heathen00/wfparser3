package com.nickmlanglois.localizer;

import com.nickmlanglois.wrap.WrapperFactory;

interface ConfigurableWrapperFactory {
  void setWrapperFactory(WrapperFactory wrapperFactory);

  WrapperFactory getWrapperFactory();
}
