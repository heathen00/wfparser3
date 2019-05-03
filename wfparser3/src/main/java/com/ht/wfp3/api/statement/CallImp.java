package com.ht.wfp3.api.statement;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class CallImp extends StatementImp implements Call {
  private class ArgumentsComparator implements Comparator<List<Integer>> {
    ArgumentsComparator() {

    }

    @Override
    public int compare(List<Integer> o1, List<Integer> o2) {
      int compare = (o1.size() < o2.size() ? -1 : (o1.size() > o2.size() ? 1 : 0));
      if (0 == compare) {
        for (int i = 0; i < o1.size(); i++) {
          compare = Integer.compare(o1.get(i), o2.get(i));
          if (0 != compare) {
            break;
          }
        }
      }
      return compare;
    }
  }

  private static final String KEYWORD = "call";

  private final boolean isFrameNumberRequired;
  private final Path fileName;
  private final List<Integer> arguments;

  CallImp(boolean isFrameNumberRequired, Path fileName, List<Integer> arguments) {
    super(KEYWORD);
    if (null == fileName) {
      throw new NullPointerException("filename constructor parameter cannot be null");
    }
    if (null == arguments) {
      throw new NullPointerException("arguments constructor parameter cannot be null");
    }

    PathMatcher matcher =
        FileSystems.getDefault().getPathMatcher("glob:*.{" + OBJ_EXT + "," + MOD_EXT + "}");
    if (!matcher.matches(fileName.getFileName())) {
      throw new IllegalArgumentException(
          "fileName must have extension '" + OBJ_EXT + "' or '" + MOD_EXT + "'");
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
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      Call call = (Call) o;
      compareTo = Boolean.compare(isFrameNumberRequired, call.isFrameNumberRequired());
      if (0 == compareTo) {
        compareTo = fileName.compareTo(call.getFileName());
        if (0 == compareTo) {
          ArgumentsComparator argumentsComparator = new ArgumentsComparator();
          compareTo = argumentsComparator.compare(arguments, call.getArguments());
        }
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
