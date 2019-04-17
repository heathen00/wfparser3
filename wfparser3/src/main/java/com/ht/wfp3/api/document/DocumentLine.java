package com.ht.wfp3.api.document;

import com.ht.wfp3.api.statement.Statement;

public interface DocumentLine {
  Comment getComment();

  Statement getStatement();
}

