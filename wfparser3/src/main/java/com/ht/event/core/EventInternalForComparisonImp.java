package com.ht.event.core;

final class EventInternalForComparisonImp implements EventInternal {
  private Channel channel;
  private String family;
  private String name;
  private Subject subject;

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
    result = prime * result + getFullyQualifiedName().hashCode() + getSubject().hashCode();
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
    if (!Event.class.isInstance(obj)) {
      return false;
    }
    Event other = (Event) obj;
    if (!getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return false;
    }
    if (!getSubject().equals(other.getSubject())) {
      return false;
    }
    return true;
  }

  @Override
  public int compareTo(Event o) {
    int compareTo = getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
    if (0 == compareTo) {
      compareTo = getSubject().compareTo(o.getSubject());
    }
    return compareTo;
  }


  @Override
  public String toString() {
    return "EventInternalForComparisonImp [getFullyQualifiedName()=" + getFullyQualifiedName()
        + ", getSubject()=" + getSubject() + "]";
  }

  @Override
  public Subject getSubject() {
    return subject;
  }

  @Override
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
