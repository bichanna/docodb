package com.bichanna.docodb
package storage

import collection.Document

import scala.collection.mutable

/**
 * A storage class for storing Document in memory
 *
 * @param memory
 */
class MemoryStorage(private var memory: Document) extends Storage:
  def this() = this(Document(mutable.Map.empty))

  override def read(): Option[Document] = Some(memory)

  override def write(document: Document): Unit = memory = document

  override def close(): Unit = () // Does not need to be implemented