package com.ht.wfp3.api;

import java.util.List;

/**
 * A polynomial and free-form geometry statement to specify a list of one or more group names to the
 * elements that follow it.
 * 
 * g group_name1 group_name2 . . .
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies the group name for the elements that follow it. You can have multiple group names. If
 * there are multiple groups on one line, the data that follows belong to all groups. Group
 * information is optional.
 * 
 * group_name is the name for the group. Letters, numbers, and combinations of letters and numbers
 * are accepted for group names. The default group name is default.
 * 
 * @author nickl
 *
 */
public interface GroupNameList extends Statement, Commentable {

  void appendGroupName(String groupName);

  List<String> getGroupNameList();

}
