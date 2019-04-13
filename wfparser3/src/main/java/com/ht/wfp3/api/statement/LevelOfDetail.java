package com.ht.wfp3.api.statement;

/**
 * The level of detail polygonal and free-form geometry statement.
 * 
 * lod level
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Sets the level of detail to be displayed in a PreView animation. The level of detail feature lets
 * you control which elements of an object are displayed while working in PreView.
 * 
 * level is the level of detail to be displayed. When you set the level of detail to 0 or omit the
 * lod statement, all elements are displayed. Specifying an integer between 1 and 100 sets the level
 * of detail to be displayed when reading the .obj file.
 * 
 * @author nickl
 *
 */
public interface LevelOfDetail extends Statement {
  static final Integer MAX = Integer.valueOf(100);
  static final Integer MIN = Integer.valueOf(1);

  Integer getLevelOfDetail();
}
