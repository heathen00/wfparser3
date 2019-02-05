package com.ht.wfp3.api;

/*
 * A sub-statement is an incomplete statement that is a part a statement. It cannot be added to a
 * document directly but is added to a statement. Comments are sub-statments and are added to other
 * statments that can be added to documents directly. All statements, either full or sub statements,
 * have a keyword.
 */
public interface SubStatment {
  String getKeyword();
}
