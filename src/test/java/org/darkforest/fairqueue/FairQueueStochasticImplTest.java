/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue;

import org.darkforest.fairqueue.impl.FairQueueStochasticImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FairQueueStochasticImplTest {
  private FairQueue<Integer> queue;

  @BeforeEach
  public void setup() {
    queue = new FairQueueStochasticImpl<>(5);
  }

  @Test
  public void fairQueueTest() {
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertNull(queue.remove());

    queue.add(8);
    queue.add(5);
    queue.add(99);
    queue.add(2);
    queue.add(45);
    queue.add(0);
    queue.add(2);
    queue.add(12);

    assertEquals(8, queue.size());
    assertFalse(queue.isEmpty());

    ArrayList<Integer> extracted = new ArrayList<>(queue.size());
    while (queue.size() != 0) {
      extracted.add(queue.remove());
    }
    assertNull(queue.remove());

    assertEquals(8, extracted.size());
    assertFalse(SortHelper.isForwardSorted(extracted));
    extracted.sort(null);

    assertEquals(0, (int) extracted.get(0));
    assertEquals(2, (int) extracted.get(1));
    assertEquals(2, (int) extracted.get(2));
    assertEquals(5, (int) extracted.get(3));
    assertEquals(8, (int) extracted.get(4));
    assertEquals(12, (int) extracted.get(5));
    assertEquals(45, (int) extracted.get(6));
    assertEquals(99, (int) extracted.get(7));
  }

  @Test
  public void fairQueueClear() {
    IntStream.range(0, 100).forEach(queue::add);

    assertEquals(100, queue.size());
    queue.clear();
    assertEquals(0, queue.size());
  }
}
