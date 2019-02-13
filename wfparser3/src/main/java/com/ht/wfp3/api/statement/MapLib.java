package com.ht.wfp3.api.statement;

import java.util.List;

/**
 * The maplib polygonal and free-form geometry statement specifies which texture maps should be
 * applied to the elements following this statement.
 * 
 * maplib filename1 filename2 . . .
 * 
 * This is a rendering identifier that specifies the map library file for the texture map
 * definitions set with the usemap identifier. You can specify multiple filenames with maplib. If
 * multiple filenames are specified, the first file listed is searched first for the map definition,
 * the second file is searched next, and so on.
 * 
 * When you assign a map library using the Model program, Model allows only one map library per .obj
 * file. You can assign multiple libraries using a text editor.
 * 
 * filename is the name of the library file where the texture maps are defined. There is no default.
 * 
 * 
 * @author nickl
 *
 */
public interface MapLib extends Statement, Commentable {
  List<String> getMapLibFileNameList();

  void appendMapLibFileName(String string);
}
