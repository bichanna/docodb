package com.bichanna.docodb
package util

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