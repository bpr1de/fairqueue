/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue.impl;

import org.darkforest.fairqueue.FairQueue;

import java.util.ArrayList;
import java.util.Random;

/**
 * An implementation of {@link FairQueue} using a single array with elements
 * extracted in random order.
 *
 * @param <T> The type of object stored in the queue.
 */
public class FairQueueRandomImpl<T> implements FairQueue<T> {
  private final ArrayList<T> queue = new ArrayList<>();
  private final Random rand = new Random();

  @Override
  public void add(T newValue) {
    queue.add(newValue);
  }

  @Override
  public int size() {
    return queue.size();
  }

  @Override
  public T remove() {
    if (queue.size() == 0) {
      return null;
    }

    return queue.remove(rand.nextInt(queue.size()));
  }

  @Override
  public void clear() {
    queue.clear();
  }
}
