package com.bichanna.docodb
package storage

import util.{DocoMapping, DocoValue}

import scala.collection.mutable

/**
 * A storage class for storing Document in memory
 *
 * @param memory
 */
class MemoryStorage(private var memory: DocoMapping) extends Storage:
  def this() = this(DocoMapping(mutable.Map.empty))

  override def read(): Option[DocoMapping] = Some(memory)

  override def write(document: DocoMapping): Unit = memory = document

  override def close(): Unit = () // Does not need to be implemented