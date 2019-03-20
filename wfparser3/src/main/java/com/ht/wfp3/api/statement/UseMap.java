package com.ht.wfp3.api.statement;

/**
 * The use map polygonal and free-form geometry statement.
 * 
 * usemap map_name/off
 * 
 * This is a rendering identifier that specifies the texture map name for the element following it.
 * To turn off texture mapping, specify off instead of the map name.
 * 
 * If you specify texture mapping for a face without texture vertices, the texture map will be
 * ignored.
 * 
 * map_name is the name of the texture map.
 * 
 * off turns off texture mapping. The default is off.
 * 
 * @author nickl
 *
 */
public interface UseMap extends Statement {
  boolean isEnabled();

  String getMapName();
}
