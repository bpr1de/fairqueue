# Fair Queue

A simple Java library for abstracting different types of fair queues behind
a common interface. This allows for building applications which are agnostic
of the implementation used for determining the order in which elements are
extracted from the queue.

Examples of backing store implementations include:

- **Priority** - Items are extracted from the queue in their natural (or 
  custom) sort order.
- **Random** - Items are extracted in random order.
- **Stochastic** - Items are extracted in round-robin fashion from a series 
  of hash tables.

## Usage

See the usage described in the `FairQueue.java` interface.

The classes and unit tests can be built using Maven e.g: `mvn package`

## License

Copyright © 2022 Bryan Phillippe, <bp@darkforest.org>

This program and the accompanying materials are made available under the
BSD license. https://opensource.org/licenses/BSD-3-Clause
