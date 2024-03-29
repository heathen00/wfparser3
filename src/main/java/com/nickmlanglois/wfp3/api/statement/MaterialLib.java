package com.nickmlanglois.wfp3.api.statement;

import java.nio.file.Path;
import java.util.List;

/**
 * The material library polygonal and free-form geometry statement.
 * 
 * mtllib filename1 filename2 . . .
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies the material library file for the material definitions set with the usemtl statement.
 * You can specify multiple filenames with mtllib. If multiple filenames are specified, the first
 * file listed is searched first for the material definition, the second file is searched next, and
 * so on.
 * 
 * When you assign a material library using the Model program, only one map library per .obj file is
 * allowed. You can assign multiple libraries using a text editor.
 * 
 * filename is the name of the library file that defines the materials. There is no default.
 * 
 * @author nickl
 *
 */
public interface MaterialLib extends Comparable<MaterialLib>, Statement {
  static final Integer MINIMUM_FILE_NAMES = Integer.valueOf(1);

  List<Path> getMaterialLibFileNameList();
}
