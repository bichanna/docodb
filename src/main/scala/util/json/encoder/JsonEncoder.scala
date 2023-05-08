package com.bichanna.docodb
package util.json.encoder

import util.json.*

/**
 * A trait for encoding objects to JSON
 * @tparam A The type of the object to encode
 */
trait JsonEncoder[A]:
  def encode(value: A): JsonValue

object JsonEncoder:
  def apply[A](implicit enc: JsonEncoder[A]): JsonEncoder[A] = enc

  implicit val stringEncoder: JsonEncoder[String] = (value: String) => JsonString(value)

  implicit val intEncoder: JsonEncoder[Int] = (value: Int) => JsonNumber(BigDecimal(value))

  implicit val doubleEncoder: JsonEncoder[Double] = (value: Double) => JsonNumber(BigDecimal(value))

  implicit val booleanEncoder: JsonEncoder[Boolean] = (value: Boolean) => JsonBoolean(value)

  implicit def optionEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Option[A]] = (value: Option[A]) =>
    value.map(enc.encode).getOrElse(JsonNull)

  implicit def listEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[List[A]] = (value: List[A]) =>
    JsonArray(value.map(enc.encode))

  implicit def mapEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Map[String, A]] = (value: Map[String, A]) =>
    JsonObject(value.map { case (k, v) => (k, enc.encode(v)) })