package com.bichanna.docodb
package collection

import util.DocoMapping

import scala.collection.mutable

/**
 * A document stored in database
 * This class provides a way to access both a document's content and its ID using `docId`.
 *
 * @param map The actual content
 * @tparam String    The type of the key
 * @tparam DocoValue The type of the value
 */
class Document[String, DocoValue](id: Int, map: mutable.Map[String, DocoValue]) extends mutable.Map[String, DocoValue]:
  val doc: mutable.Map[String, DocoValue] = map

  def docId: Int = id

  override def get(key: String): Option[DocoValue] = doc.get(key)

  override def iterator: Iterator[(String, DocoValue)] = doc.iterator

  override def subtractOne(elem: String): Document.this.type =
    doc.subtractOne(elem)
    this

  override def addOne(elem: (String, DocoValue)): Document.this.type =
    doc.addOne(elem)
    this