package com.nickmlanglois.wfp3.api.statement;

/**
 * A polygonal and free-form geometry statement to add a smoothing group to the elements that follow
 * it.
 * 
 * s group_number
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Sets the smoothing group for the elements that follow it. If you do not want to use a smoothing
 * group, specify off or a value of 0.
 * 
 * To display with smooth shading in Model and PreView, you must create vertex normals after you
 * have assigned the smoothing groups. You can create vertex normals with the vn statement or with
 * the Model program.
 * 
 * To smooth polygonal geometry for rendering with Image, it is sufficient to put elements in some
 * smoothing group. However, vertex normals override smoothing information for Image.
 * 
 * group_number is the smoothing group number. To turn off smoothing groups, use a value of 0 or
 * off. Polygonal elements use group numbers to put elements in different smoothing groups. For
 * free-form surfaces, smoothing groups are either turned on or off; there is no difference between
 * values greater than 0.
 * 
 * @author nickl
 *
 */
public interface SmoothingGroup extends Comparable<SmoothingGroup>, Statement {
  boolean isEnabled();

  Integer getSmoothingGroupNumber();
}
