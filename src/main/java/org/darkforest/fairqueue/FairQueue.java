/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue;

/**
 * A simple interface for queueing: upon removal, items in the queue will be
 * distributed in an order determined by the type of backing implementation,
 * such as FIFO, LIFO, Stochastic, random, etc.
 *
 * @param <T> The type of items to contain the FairQueue.
 */
public interface FairQueue<T> {
  /**
   * Adds an item to the queue.
   *
   * @param newValue the item to add.
   */
  void add(T newValue);

  /**
   * The size of the queue.
   *
   * @return the number of items present in the queue.
   */
  int size();

  /**
   * Tests whether the queue is empty.
   *
   * @return true if there are no items present in the queue.
   */
  default boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Removes an item from the queue.
   *
   * @return an item determined by the backing implementation, or null if no
   *     items remain in the queue.
   */
  T remove();

  /**
   * Remove all items from the queue.
   */
  default void clear() {
    while (!isEmpty()) {
      remove();
    }
  }
}
