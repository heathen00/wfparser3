package com.ht.wfp3.api.document;

import com.ht.wfp3.api.statement.Statement;

public class DocumentFactory {
  private static final DocumentFactory DOCUMENT_FACTORY_SINGLETON = new DocumentFactory();

  public static DocumentFactory createDocumentFactory() {
    return DOCUMENT_FACTORY_SINGLETON;
  }

  public Document createObjDocument() {
    return new DocumentImp();
  }

  DocumentLine createObjDocumentLine(Statement statement, Comment comment) {
    return new DocumentLineImp(statement, comment);
  }

  Comment createComment(String commentString) {
    return new CommentImp(commentString);
  }
}
