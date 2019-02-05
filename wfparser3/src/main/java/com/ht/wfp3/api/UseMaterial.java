package com.ht.wfp3.api;

/**
 * The use material polygonal and free-form geometry statement.
 * 
 * usemtl material_name
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies the material name for the element following it. Once a material is assigned, it cannot
 * be turned off; it can only be changed.
 * 
 * material_name is the name of the material. If a material name is not specified, a white material
 * is used.
 * 
 * @author nickl
 *
 */
public interface UseMaterial extends Node {

}
