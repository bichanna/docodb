package com.bichanna.docodb
package util.json.encoder

import util.*
import util.json.*

/**
 * A trait for encoding objects to JSON
 *
 * @tparam A The type of the object to encode
 */
trait JsonEncoder[A]:
  def encode(value: A): DocoValue

object JsonEncoder:
  def apply[A](implicit enc: JsonEncoder[A]): JsonEncoder[A] = enc

  implicit val stringEncoder: JsonEncoder[String] = (value: String) => DocoString(value)

  implicit val intEncoder: JsonEncoder[Int] = (value: Int) => DocoNumber(BigDecimal(value))

  implicit val doubleEncoder: JsonEncoder[Double] = (value: Double) => DocoNumber(BigDecimal(value))

  implicit val booleanEncoder: JsonEncoder[Boolean] = (value: Boolean) => DocoBoolean(value)

  implicit def optionEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Option[A]] = (value: Option[A]) =>
    value.map(enc.encode).getOrElse(DocoNull)

  implicit def listEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[List[A]] = (value: List[A]) =>
    DocoList(value.map(enc.encode))

  implicit def mapEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Map[String, A]] = (value: Map[String, A]) =>
    DocoMapping(value.map { case (k, v) => (k, enc.encode(v)) })