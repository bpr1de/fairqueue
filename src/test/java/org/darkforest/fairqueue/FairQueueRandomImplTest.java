/*
 * Copyright (c) 2020, Bryan Phillippe (bp@darkforest.org)
 * May be used under the terms of the BSD license.
 */

package org.darkforest.fairqueue;

import org.darkforest.fairqueue.impl.FairQueueRandomImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class FairQueueRandomImplTest {
  private FairQueue<Integer> queue;

  @BeforeEach
  public void setup() {
    queue = new FairQueueRandomImpl<>();
  }

  @Test
  public void fairQueueTest() {
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());
    assertNull(queue.remove());

    IntStream.range(0, 15).forEach(queue::add);

    assertEquals(15, queue.size());
    assertFalse(queue.isEmpty());

    ArrayList<Integer> extracted = new ArrayList<>();
    while (!queue.isEmpty()) {
      extracted.add(queue.remove());
    }

    assertEquals(15, extracted.size());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());

    assertFalse(SortHelper.isSorted(extracted));
  }

  @Test
  public void fairQueueClear() {
    IntStream.range(0, 100).forEach(queue::add);

    assertEquals(100, queue.size());
    queue.clear();
    assertEquals(0, queue.size());
  }
}