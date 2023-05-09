package com.bichanna.docodb
package util.json.parser

import util.json.*
import util.*

/**
 * A concrete implementation of the JsonParser trait using Circe
 */
object CirceParser extends JsonParser:

  import io.circe.{Json, parser}

  override def parse(json: String): Either[Throwable, DocoValue] = parser.parse(json) match
    case Left(err) => Left(err)
    case Right(json) => Right(toJsonValue(json))

  private def toJsonValue(json: Json): DocoValue = json.fold(
    DocoNull,
    DocoBoolean,
    num => DocoNumber(num.toBigDecimal.getOrElse(throw Exception(s"invalid number: $num"))),
    DocoString,
    arr => DocoList(arr.map(toJsonValue)),
    obj => DocoMapping(obj.toMap.view.mapValues(toJsonValue).toMap)
  )