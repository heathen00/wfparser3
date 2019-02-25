package com.ht.wfp3.api.statement;

/**
 * Execute the specified c shell compatible UNIX command.
 * 
 * csh command
 * 
 * csh -command
 * 
 * Executes the requested UNIX command. If the UNIX command returns an error, the parser flags an
 * error during parsing.
 * 
 * If a dash (-) precedes the UNIX command, the error is ignored.
 * 
 * command is the UNIX command.
 * 
 * @author nickl
 *
 */
public interface Csh extends Statement {
  boolean shouldIgnoreError();

  String getCommand();
}
