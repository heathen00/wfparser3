package com.nickmlanglois.wfp3.api.statement;

import java.util.Comparator;
import java.util.List;

class ListOfComparableComparator<T extends Comparable<T>> implements Comparator<List<T>> {
  ListOfComparableComparator() {}

  @Override
  public int compare(List<T> o1, List<T> o2) {
    int compare = (o1.size() < o2.size() ? -1 : (o1.size() > o2.size() ? 1 : 0));
    if (0 == compare) {
      for (int i = 0; i < o1.size(); i++) {
        compare = o1.get(i).compareTo(o2.get(i));
        if (0 != compare) {
          break;
        }
      }
    }
    return compare;
  }
}
