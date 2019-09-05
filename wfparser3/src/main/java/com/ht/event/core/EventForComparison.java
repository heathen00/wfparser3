package com.ht.event.core;

final class EventForComparison implements Event {
  private Channel channel;
  private String family;
  private String name;

  @Override
  public Channel getChannel() {
    return channel;
  }

  @Override
  public String getFamily() {
    return family;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  public void setFamily(String family) {
    this.family = family;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", channel.getName(), getFamily(), getName());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getFullyQualifiedName().hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    EventImp other = (EventImp) obj;
    if (!getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Event o) {
    return getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
  }
}
