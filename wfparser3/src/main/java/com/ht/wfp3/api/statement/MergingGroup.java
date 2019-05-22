package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * A free-form-geometry statement to specify the merging group that the following elements belong
 * to.
 * 
 * mg group_number res
 * 
 * Free-form geometry statement.
 * 
 * Sets the merging group and merge resolution for the free-form surfaces that follow it. If you do
 * not want to use a merging group, specify off or a value of 0.
 * 
 * Adjacency detection is performed only within groups, never between groups. Connectivity between
 * surfaces in different merging groups is not allowed. Surfaces in the same merging group are
 * merged together along edges that are within the distance res apart.
 * 
 * NOTE: Adjacency detection is an expensive numerical comparison process. It is best to restrict
 * this process to as small a domain as possible by using small merging groups.
 * 
 * group_number is the merging group number. To turn off adjacency detection, use a value of 0 or
 * off.
 * 
 * res is the maximum distance between two surfaces that will be merged together. The resolution
 * must be a value greater than 0. This is a required argument only when using merging groups.
 * 
 * @author nickl
 *
 */
public interface MergingGroup extends Comparable<MergingGroup>, Statement {
  static final Integer OFF = Integer.valueOf(0);
  static final BigDecimal MINIMUM_RESOLUTION = BigDecimal.ZERO;

  boolean isEnabled();

  Integer getMergingGroupNumber();

  BigDecimal getMergingResolution();
}
