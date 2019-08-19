package com.ht.wfp3.api.statement;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CallImp extends StatementImp implements Call {
  private static final String KEYWORD = "call";

  private final boolean isFrameNumberRequired;
  private final Path fileName;
  private final List<Integer> arguments;

  CallImp(boolean isFrameNumberRequired, Path fileName, List<Integer> arguments) {
    super(KEYWORD);
    if (null == fileName) {
      throw new NullPointerException("fileName cannot be null");
    }
    if (null == arguments) {
      throw new NullPointerException("arguments cannot be null");
    }
    PathMatcher matcher =
        FileSystems.getDefault().getPathMatcher("glob:*.{" + OBJ_EXT + "," + MOD_EXT + "}");
    if (!matcher.matches(fileName.getFileName())) {
      throw new IllegalArgumentException(
          "fileName must have extension '" + OBJ_EXT + "' or '" + MOD_EXT + "'");
    }
    if (arguments.contains(null)) {
      throw new IllegalArgumentException("arguments cannot contain null members");
    }
    this.isFrameNumberRequired = isFrameNumberRequired;
    this.fileName = fileName;
    this.arguments = new ArrayList<Integer>(arguments);
  }

  CallImp(Call call) {
    this(call.isFrameNumberRequired(), call.getFileName(), call.getArguments());
  }

  @Override
  public boolean isFrameNumberRequired() {
    return isFrameNumberRequired;
  }

  @Override
  public Path getFileName() {
    return fileName;
  }

  @Override
  public List<Integer> getArguments() {
    return Collections.unmodifiableList(arguments);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
    result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
    result = prime * result + (isFrameNumberRequired ? 1231 : 1237);
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
    CallImp other = (CallImp) obj;
    if (arguments == null) {
      if (other.arguments != null)
        return false;
    } else if (!arguments.equals(other.arguments))
      return false;
    if (fileName == null) {
      if (other.fileName != null)
        return false;
    } else if (!fileName.equals(other.fileName))
      return false;
    if (isFrameNumberRequired != other.isFrameNumberRequired)
      return false;
    return true;
  }

  @Override
  public int compareTo(Call o) {
    int compareTo = Boolean.compare(isFrameNumberRequired, o.isFrameNumberRequired());
    if (0 == compareTo) {
      compareTo = fileName.compareTo(o.getFileName());
      if (0 == compareTo) {
        ListOfComparableComparator<Integer> listComparator = new ListOfComparableComparator<>();
        compareTo = listComparator.compare(arguments, o.getArguments());
      }
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "CallImp [isFrameNumberRequired=" + isFrameNumberRequired + ", fileName=" + fileName
        + ", arguments=" + arguments + ", super.toString()=" + super.toString() + "]";
  }
}
