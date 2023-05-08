package com.bichanna.docodb
package util.json.parser

import util.json.*

/**
 * A concrete implementation of the JsonParser trait using Circe
 */
object CirceParser extends JsonParser:

  import io.circe.{Json, parser}

  override def parse(json: String): Either[Throwable, JsonValue] = parser.parse(json) match
    case Left(err) => Left(err)
    case Right(json) => Right(toJsonValue(json))

  private def toJsonValue(json: Json): JsonValue = json.fold(
    JsonNull,
    JsonBoolean,
    num => JsonNumber(num.toBigDecimal.getOrElse(throw Exception(s"invalid number: $num"))),
    JsonString,
    arr => JsonArray(arr.map(toJsonValue)),
    obj => JsonObject(obj.toMap.view.mapValues(toJsonValue).toMap)
  )