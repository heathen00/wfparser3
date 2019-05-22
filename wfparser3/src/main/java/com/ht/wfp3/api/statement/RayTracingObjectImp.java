package com.ht.wfp3.api.statement;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

class RayTracingObjectImp extends StatementImp implements RayTracingObject {
  private static final String KEYWORD = "trace_obj";

  private final Path rayTracingObjectFileName;

  RayTracingObjectImp(Path rayTracingObjectFileName) {
    super(KEYWORD);
    if (null == rayTracingObjectFileName) {
      throw new NullPointerException(
          "rayTracingObjectFileName constructor parameter cannot be null");
    }
    if (!rayTracingObjectFileName.getFileName().toFile().getName().contains(".")) {
      rayTracingObjectFileName =
          rayTracingObjectFileName.resolveSibling(rayTracingObjectFileName.getFileName() + ".obj");
    }
    PathMatcher supportedExtensionsMatcher =
        FileSystems.getDefault().getPathMatcher("glob:*.{" + OBJ_EXT + "," + MOD_EXT + "}");
    if (!supportedExtensionsMatcher.matches(rayTracingObjectFileName.getFileName())) {
      throw new IllegalArgumentException(
          "fileName must have extension '" + OBJ_EXT + "' or '" + MOD_EXT + "'");
    }
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
  public int compareTo(RayTracingObject o) {
    return rayTracingObjectFileName.compareTo(o.getRayTracingObjectFileName());
  }

  @Override
  public String toString() {
    return "RayTracingObjectImp [rayTracingObjectFileName=" + rayTracingObjectFileName
        + ", super.toString()=" + super.toString() + "]";
  }
}
