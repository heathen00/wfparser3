package com.ht.wfp3.api.acceptance;

import com.ht.wfp3.api.Statement;

import java.util.List;

/**
 * An unknown statement has been encountered in the OBJ file.  This is not a part of the OBJ specification
 * but represents an unknown statement if one has been encountered while parsing the OBJ file and the
 * programmer chooses to allow the API to handle the error.  It is best to pass in all tokens.
 * 
 * @author nickl
 *
 */
public interface Unknown extends Statement {
  List<String> getTokens();
}
