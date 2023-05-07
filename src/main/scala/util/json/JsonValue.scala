package com.bichanna.docodb
package util.json

sealed trait JsonValue:
  def asJson: String

case object JsonNull extends JsonValue:
  override def asJson: String = "null"

case class JsonBoolean(var value: Boolean) extends JsonValue:
  override def asJson: String = value.toString

case class JsonNumber(var value: BigDecimal) extends JsonValue:
  override def asJson: String = value.toString()

case class JsonString(var value: String) extends JsonValue:
  override def asJson: String = s"\"$value\""

case class JsonArray(var elements: Seq[JsonValue]) extends JsonValue:
  override def asJson: String = s"[${elements.map(_.asJson).mkString(", ")}]"

case class JsonObject(var pairs: Map[String, JsonValue]) extends JsonValue:
  override def asJson: String = s"{${pairs.map((k, v) => s"\"$k\" : ${v.asJson}").mkString(", ")}}"