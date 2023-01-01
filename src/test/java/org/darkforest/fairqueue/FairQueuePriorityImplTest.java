/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue;

import org.darkforest.fairqueue.impl.FairQueuePriorityImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FairQueuePriorityImplTest {
  private FairQueue<Integer> queue;

  @BeforeEach
  public void setup() {
    queue = new FairQueuePriorityImpl<>();
  }

  @Test
  public void fairQueueTest() {
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertNull(queue.remove());

    queue.add(9);
    queue.add(14);
    queue.add(-1);
    queue.add(0);
    queue.add(9);
    queue.add(9);
    queue.add(0);
    queue.add(2);
    queue.add(-256);
    queue.add(100);
    queue.add(1);

    assertEquals(11, queue.size());
    assertFalse(queue.isEmpty());

    ArrayList<Integer> extracted = new ArrayList<>();
    while (!queue.isEmpty()) {
      extracted.add(queue.remove());
    }

    assertEquals(11, extracted.size());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());

    assertTrue(SortHelper.isForwardSorted(extracted));
  }

  @Test
  public void fairQueueTestCustom() {
    queue = new FairQueuePriorityImpl<>((a, b) -> b - a);

    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertNull(queue.remove());

    queue.add(9);
    queue.add(14);
    queue.add(-1);
    queue.add(0);
    queue.add(9);
    queue.add(9);
    queue.add(0);
    queue.add(2);
    queue.add(-256);
    queue.add(100);
    queue.add(1);

    assertEquals(11, queue.size());
    assertFalse(queue.isEmpty());

    ArrayList<Integer> extracted = new ArrayList<>();
    while (!queue.isEmpty()) {
      extracted.add(queue.remove());
    }

    assertEquals(11, extracted.size());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());

    assertTrue(SortHelper.isReverseSorted(extracted));
  }

  @Test
  public void fairQueueClear() {
    IntStream.range(0, 100).forEach(queue::add);

    assertEquals(100, queue.size());
    queue.clear();
    assertEquals(0, queue.size());
  }
}
