package com.ht.wfp3.api.statement;

import java.nio.file.Path;
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
}
