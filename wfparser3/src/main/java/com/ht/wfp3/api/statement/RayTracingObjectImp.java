package com.ht.wfp3.api.statement;

import java.nio.file.Path;

class RayTracingObjectImp extends StatementImp implements RayTracingObject {
  private static final String KEYWORD = "trace_obj";

  private final Path rayTracingObjectFileName;

  RayTracingObjectImp(Path rayTracingObjectFileName) {
    super(KEYWORD);
    this.rayTracingObjectFileName = rayTracingObjectFileName;
  }

  RayTracingObjectImp(RayTracingObject trace_obj) {
    this(trace_obj.getRayTracingObjectFileName());
  }

  @Override
  public Path getRayTracingObjectFileName() {
    return rayTracingObjectFileName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((rayTracingObjectFileName == null) ? 0 : rayTracingObjectFileName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    RayTracingObjectImp other = (RayTracingObjectImp) obj;
    if (rayTracingObjectFileName == null) {
      if (other.rayTracingObjectFileName != null)
        return false;
    } else if (!rayTracingObjectFileName.equals(other.rayTracingObjectFileName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "RayTracingObjectImp [rayTracingObjectFileName=" + rayTracingObjectFileName
        + ", super.toString()=" + super.toString() + "]";
  }
}
