package com.bichanna.docodb
package collection

import util.DocoValue

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

object Document:
  private var nextId: Int = 0

  private def newNextId: Int =
    nextId += 1
    nextId