/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue;

import java.util.ArrayList;

public class SortHelper {

  /**
   * Determines if the ArrayList is sorted in natural order.
   *
   * @param array Input ArrayList.
   * @return true if sorted in ascending order, false otherwise.
   */
  public static boolean isSorted(final ArrayList<Integer> array) {
    for (int i = 0; i < array.size() - 1; ++i) {
      if (array.get(i).compareTo(array.get(i + 1)) > 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * Determines if the ArrayList is sorted in reverse natural order.
   *
   * @param array Input ArrayList.
   * @return true if sorted in descending order, false otherwise.
   */
  public static boolean isReverseSorted(final ArrayList<Integer> array) {
    for (int i = 0; i < array.size() - 1; ++i) {
      if (array.get(i).compareTo(array.get(i + 1)) < 0) {
        return false;
      }
    }

    return true;
  }
}
