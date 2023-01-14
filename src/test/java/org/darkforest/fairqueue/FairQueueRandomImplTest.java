/*
 * Copyright (c) 2020-2023, Bryan Phillippe <bp@darkforest.org>
 * See LICENSE for terms of use.
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

    IntStream.range(0, 100).forEach(queue::add);

    assertEquals(100, queue.size());
    assertFalse(queue.isEmpty());

    ArrayList<Integer> extracted = new ArrayList<>();
    while (!queue.isEmpty()) {
      extracted.add(queue.remove());
    }

    assertEquals(100, extracted.size());
    assertEquals(0, queue.size());
    assertTrue(queue.isEmpty());

    /*
     * There is a very small chance this test can fail, given the random
     * nature of this queue. Using a large enough queue should kill the odds.
     */
    assertFalse(SortHelper.isForwardSorted(extracted));
  }

  @Test
  public void fairQueueClear() {
    IntStream.range(0, 100).forEach(queue::add);

    assertEquals(100, queue.size());
    queue.clear();
    assertEquals(0, queue.size());
  }
}
