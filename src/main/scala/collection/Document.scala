package com.bichanna.docodb
package collection

import util.DocoMapping

import scala.collection.mutable

class Document[String, DocoValue](map: mutable.Map[String, DocoValue]) extends mutable.Map[String, DocoValue]:
  val doc: mutable.Map[String, DocoValue] = map

  override def get(key: String): Option[DocoValue] = doc.get(key)

  override def iterator: Iterator[(String, DocoValue)] = doc.iterator

  override def subtractOne(elem: String): Document.this.type =
    doc.subtractOne(elem)
    this

  override def addOne(elem: (String, DocoValue)): Document.this.type =
    doc.addOne(elem)
    this