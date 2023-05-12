package com.bichanna.docodb
package storage

import util.{DocoMapping, DocoValue}

import scala.collection.mutable

/**
 * A storage class for storing Document in memory
 *
 * @param memory
 */
class MemoryStorage(private var memory: DocoValue) extends Storage:
  def this() = this(DocoMapping(mutable.Map.empty))

  override def read(): Option[DocoValue] = Some(memory)

  override def write(value: DocoValue): Unit = memory = value

  override def close(): Unit = () // Does not need to be implemented