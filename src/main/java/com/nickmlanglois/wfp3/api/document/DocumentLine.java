package com.nickmlanglois.wfp3.api.document;

import com.nickmlanglois.wfp3.api.statement.Statement;

public interface DocumentLine {
  Comment getComment();

  Statement getStatement();
}

