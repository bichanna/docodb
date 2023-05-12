package com.bichanna.docodb
package util

import scala.collection.mutable.Map
import scala.language.implicitConversions

/**
 * A custom representation for different data formats like JSON and YAML
 */
sealed trait DocoValue

case object DocoNull extends DocoValue

case class DocoBoolean(var value: Boolean) extends DocoValue

case class DocoNumber(var value: BigDecimal) extends DocoValue

case class DocoString(var value: String) extends DocoValue

case class DocoList(var elements: Seq[DocoValue]) extends DocoValue

case class DocoMapping(var pairs: Map[String, DocoValue]) extends DocoValue

implicit def boolToDocoBoolean(value: Boolean): DocoBoolean = DocoBoolean(value)

implicit def intToDocoNumber(value: Int): DocoNumber = DocoNumber(BigDecimal(value))

implicit def doubleToDocoNumber(value: Double): DocoNumber = DocoNumber(BigDecimal(value))

implicit def stringToDocoString(value: String): DocoString = DocoString(value)

implicit def seqToDocoList(seq: Seq[DocoValue]): DocoList = DocoList(seq)

implicit def mapToDocoMapping(map: Map[String, DocoValue]): DocoMapping = DocoMapping(map)