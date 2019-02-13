package com.ht.wfp3.api.statement;

import java.util.List;

/**
 * Read the contents of the specified file into the current location.
 * 
 * call filename.ext arg1 arg2 . . .
 * 
 * Reads the contents of the specified .obj or .mod file at this location. The call statement can be
 * inserted into .obj files using a text editor.
 * 
 * filename.ext is the name of the .obj or .mod file to be read. You must include the extension with
 * the filename.
 * 
 * arg1 arg2 . . . specifies a series of optional integer arguments that are passed to the called
 * file. There is no limit to the number of nested calls that can be made.
 * 
 * Arguments passed to the called file are substituted in the same way as in UNIX scripts; for
 * example, $1 in the called file is replaced by arg1, $2 in the called file is replaced by arg2,
 * and so on.
 * 
 * If the frame number is needed in the called file for variable substitution, "$1" must be used as
 * the first argument in the call statement. For example:
 * 
 * call filename.obj $1
 * 
 * Then the statement in the called file,
 * 
 * scmp filename.pv $1
 * 
 * will work as expected. For more information on the scmp statement, see appendix C, Variable
 * Substitution for more information.
 * 
 * Another method to do the same thing is:
 * 
 * scmp filename.pv $1
 * 
 * call filename.obj
 * 
 * Using this method, the scmp statement provides the .pv file for all subsequently called .obj or
 * .mod files.
 * 
 * @author nickl
 *
 */
public interface Call extends Statement, Commentable {
  String getFileName();

  List<Integer> getArguments();

  void appendArgument(String arg);
}
