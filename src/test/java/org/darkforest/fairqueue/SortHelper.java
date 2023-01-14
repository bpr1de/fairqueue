/*
 * Copyright (c) 2020-2023, Bryan Phillippe <bp@darkforest.org>
 * See LICENSE for terms of use.
 */

package org.darkforest.fairqueue;

import java.util.ArrayList;
import java.util.function.IntPredicate;

public class SortHelper {

  private static boolean forward(int value) {
    return (value <= 0);
  }

  private static boolean reverse(int value) {
    return (value >= 0);
  }

  private static boolean isSorted(final ArrayList<Integer> array,
                                  IntPredicate sortOrder) {
    for (int i = 0; i < array.size() - 1; ++i) {
      if (!sortOrder.test(array.get(i).compareTo(array.get(i + 1)))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Determines if the ArrayList is sorted in natural order.
   *
   * @param array Input ArrayList.
   * @return true if sorted in ascending order, false otherwise.
   */
  public static boolean isForwardSorted(final ArrayList<Integer> array) {
    return isSorted(array, SortHelper::forward);
  }

  /**
   * Determines if the ArrayList is sorted in reverse natural order.
   *
   * @param array Input ArrayList.
   * @return true if sorted in descending order, false otherwise.
   */
  public static boolean isReverseSorted(final ArrayList<Integer> array) {
    return isSorted(array, SortHelper::reverse);
  }
}
