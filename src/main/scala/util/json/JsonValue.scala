package com.bichanna.docodb
package util.json

sealed trait JsonValue

case object JsonNull extends JsonValue
case class JsonBoolean(value: Boolean) extends JsonValue
case class JsonNumber(value: BigDecimal) extends JsonValue
case class JsonString(value: String) extends JsonValue
case class JsonArray(elements: Seq[JsonValue]) extends JsonValue
case class JsonObject(pairs: Map[String, JsonValue]) extends JsonValue