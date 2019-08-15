package com.ht.uid;

public class StubUniqueComponent implements UniqueComponent<StubUniqueComponent> {
  private final String componentName;

  public static StubUniqueComponent createStubUniqueComponent(String testComponentName) {
    return new StubUniqueComponent(testComponentName);
  }

  private StubUniqueComponent(String componentName) {
    this.componentName = componentName;
  }

  @Override
  public Uid<StubUniqueComponent> getUid() {
    throw new UnsupportedOperationException("operation not supported by stub");
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((componentName == null) ? 0 : componentName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StubUniqueComponent other = (StubUniqueComponent) obj;
    if (componentName == null) {
      if (other.componentName != null)
        return false;
    } else if (!componentName.equals(other.componentName))
      return false;
    return true;
  }
}
