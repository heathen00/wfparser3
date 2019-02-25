package com.ht.wfp3.api.statement;

/*
 * A sub-statement is an incomplete statement that is a part of a statement. It cannot be added to a
 * document directly but is added to a statement. Comments are sub-statments and are added to other
 * statments that can be added to documents directly. All statements, either full or sub statements,
 * have a keyword.
 */
public interface SubStatement {
  String getKeyword();
}
