/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue.impl;

import org.darkforest.fairqueue.FairQueue;

import java.util.ArrayList;

/**
 * An implementation of {@link FairQueue} based on a stochastic distribution
 * across a hash table composed of a set of arrays. Each element is inserted
 * into the array corresponding to its object hash, and elements are extracted
 * one-at-a-time from each array in round-robin order.
 *
 * @param <T> The type of object stored in the queue.
 */
public class FairQueueStochasticImpl<T> implements FairQueue<T> {
  static private final int DEFAULT_QUEUE_SIZE = 10;

  private final ArrayList<ArrayList<T>> sfQueue;
  private int numberOfElements;
  private int index;

  /**
   * Default constructor - use a default number of arrays.
   */
  public FairQueueStochasticImpl() {
    this(DEFAULT_QUEUE_SIZE);
  }

  /**
   * Supply a custom number of arrays to distribute across.
   *
   * @param size The number of arrays to create.
   */
  public FairQueueStochasticImpl(int size) {
    sfQueue = new ArrayList<>(size);
    for (int i = 0; i < size; ++i) {
      sfQueue.add(new ArrayList<>());
    }
  }

  @Override
  public void add(T newValue) {
    sfQueue.get(Math.abs(newValue.hashCode() % sfQueue.size())).add(newValue);
    ++numberOfElements;
  }

  @Override
  public int size() {
    return numberOfElements;
  }

  @Override
  public T remove() {
    for (int i = 0; i < sfQueue.size(); ++i) {
      if (index == sfQueue.size()) {
        index = 0;
      }
      if (!sfQueue.get(index).isEmpty()) {
        --numberOfElements;
        return sfQueue.get(index++).remove(0);
      }
      ++index;
    }
    return null;
  }

  @Override
  public void clear() {
    for (ArrayList<T> q: sfQueue) {
      q.clear();
    }
    numberOfElements = 0;
  }
}
