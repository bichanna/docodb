package com.bichanna.docodb
package storage

import util.{DocoMapping, DocoValue}

/**
 * A trait for all Storages.
 * A Storage serializes and deserializes the state of the database and stores it in some place.
 */
trait Storage:
  /**
   * Reads the current state of the database with any kind of custom deserialization
   *
   * @return If `None` is returned, it means the storage is empty.
   */
  def read(): Option[DocoMapping]

  /**
   * Writes the current state of the database to the storage
   *
   * @param document The current data of the database
   */
  def write(document: DocoMapping): Unit

  /**
   * Closes open file handles, etc.
   */
  def close(): Unit

  override def toString: String = getClass.getSimpleName