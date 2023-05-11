package com.bichanna.docodb
package collection

import util.DocoValue

import scala.collection
import scala.collection.mutable

/**
 * A document stored in database
 * This class provides a way to access both a document's content and its ID using `docId`.
 *
 * @param map The actual content
 */
class Document(map: mutable.Map[String, DocoValue]) extends mutable.Map[String, DocoValue]:
  val doc: mutable.Map[String, DocoValue] = map
  val docId: Int = Document.newNextId

  override def get(key: String): Option[DocoValue] = doc.get(key)

  override def iterator: Iterator[(String, DocoValue)] = doc.iterator

  override def subtractOne(elem: String): Document.this.type =
    doc.subtractOne(elem)
    this

  override def addOne(elem: (String, DocoValue)): Document.this.type =
    doc.addOne(elem)
    this

  override def map[B](f: ((String, DocoValue)) => B): mutable.Iterable[B] = doc.map(f)

  override def contains(key: String): Boolean = doc.contains(key)

  override def remove(key: String): Option[DocoValue] = doc.remove(key)

  override def isDefinedAt(key: String): Boolean = doc.isDefinedAt(key)

  override def keys: Iterable[String] = doc.keys

  override def keySet: collection.Set[String] = doc.keySet

  override def keysIterator: Iterator[String] = doc.keysIterator

  override def values: Iterable[DocoValue] = doc.values

  override def valuesIterator: Iterator[DocoValue] = doc.valuesIterator

object Document:
  private var nextId: Int = 0

  private def newNextId: Int =
    nextId += 1
    nextId