/*
 * Copyright (c) 2020-2023, Bryan Phillippe <bp@darkforest.org>
 * See LICENSE for terms of use.
 */

package org.darkforest.fairqueue.impl;

import org.darkforest.fairqueue.FairQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * An implementation of the {@link FairQueue} based on a Priority Queue. The
 * priority is either natural order (default) or determined based on a supplied
 * comparator.
 *
 * @param <T> The type of object stored in the queue.
 */
public class FairQueuePriorityImpl<T> implements FairQueue<T> {
  private final PriorityQueue<T> pQueue;

  /**
   * Default constructor - use natural order of elements.
   */
  public FairQueuePriorityImpl() {
    pQueue = new PriorityQueue<>();
  }

  /**
   * Supply a custom comparator to determine priority order.
   *
   * @param comparator determines priority order.
   */
  public FairQueuePriorityImpl(Comparator<? super T> comparator) {
    pQueue = new PriorityQueue<>(comparator);
  }

  @Override
  public void add(T newValue) {
    pQueue.add(newValue);
  }

  @Override
  public int size() {
    return pQueue.size();
  }

  @Override
  public T remove() {
    return pQueue.poll();
  }

  @Override
  public void clear() {
    pQueue.clear();
  }
}
