package com.bichanna.docodb
package storage

import collection.Document
import util.*

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.Map

val path = "./src/test/scala/storage/test.db.json"

val document = Document(Map(
  "nulls" -> Seq(DocoNull, DocoNull),
  "int" -> 123,
  "double" -> 123.456789,
  "list" -> Seq(DocoNumber(1), DocoString("Hello"), DocoNull),
  "object" -> DocoMapping(Map(
    "a" -> 1,
    "b" -> "Hello",
  ))
))

class StorageTest extends AnyFunSuite:
  test("JsonStorage read write") {
    val storage = JsonStorage(path)
    storage.write(document)
    assert(storage.read().contains(document))

    storage.close()
  }

  test("MemoryStorage read write") {
    val storage = MemoryStorage()
    storage.write(document)
    assert(storage.read().contains(document))
  }