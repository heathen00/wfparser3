package com.ht.wfp3.api.statement;

class CshImp extends StatementImp implements Csh {
  private static final String KEYWORD = "csh";

  private final boolean shouldIgnoreErrors;
  private final String command;

  CshImp(boolean shouldIgnoreErrors, String command) {
    super(KEYWORD);
    if (null == command) {
      throw new NullPointerException("command constructor parameter cannot be null");
    }
    if (command.matches("^\\s*$")) {
      throw new IllegalArgumentException("command constructor parameter cannot be empty or whitespace only");
    }
    this.shouldIgnoreErrors = shouldIgnoreErrors;
    this.command = command;
  }

  CshImp(Csh csh) {
    this(csh.shouldIgnoreErrors(), csh.getCommand());
  }

  @Override
  public boolean shouldIgnoreErrors() {
    return shouldIgnoreErrors;
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((command == null) ? 0 : command.hashCode());
    result = prime * result + (shouldIgnoreErrors ? 1231 : 1237);
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
    CshImp other = (CshImp) obj;
    if (command == null) {
      if (other.command != null)
        return false;
    } else if (!command.equals(other.command))
      return false;
    if (shouldIgnoreErrors != other.shouldIgnoreErrors)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CshImp [shouldIgnoreErrors=" + shouldIgnoreErrors + ", command=" + command
        + ", super.toString()=" + super.toString() + "]";
  }
}
