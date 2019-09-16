package com.nickmlanglois.wfp3.message.OLD;

class NameMapper {
  private final String group;
  private final String type;
  private final String instance;

  NameMapper(String group, String type, String instance) {
    this.group = group;
    this.type = type;
    this.instance = instance;
  }

  String getMappedName(String method) {
    return String.join(".", group, type, method, instance);
  }
}
