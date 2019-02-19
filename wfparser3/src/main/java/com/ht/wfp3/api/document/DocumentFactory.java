package com.ht.wfp3.api.document;

public class DocumentFactory {
  private static final DocumentFactory DOCUMENT_FACTORY_SINGLETON = new DocumentFactory();
  public static DocumentFactory createDocumentFactory() {
    return DOCUMENT_FACTORY_SINGLETON;
  }
  
  public Document createObjDocument() {
    return new DocumentImp();
  }
}
