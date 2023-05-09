package com.bichanna.docodb
package util.json

import util.*

import scala.language.implicitConversions

/**
 * A custom JSON representation based on DocoValue that can be used with any JSON library
 */
class JsonValue(dv: DocoValue):
  def asJson: String = dv match
    case DocoNull => "null"
    case DocoBoolean(value) => value.toString
    case DocoNumber(value) => value.toString()
    case DocoString(value) => s"\"${value}\""
    case DocoList(elements) => s"[${elements.map(_.asJson).mkString(", ")}]"
    case DocoMapping(pairs) => s"{${pairs.map((k: String, v: DocoValue) => s"\"$k\": ${v.asJson}").mkString(", ")}}"

implicit def asJsonValue(dv: DocoValue): JsonValue = JsonValue(dv)