package com.bichanna.docodb
package util.json

import util.json.parser.CirceParser
import org.scalatest.funsuite.AnyFunSuite
class JsonParserTest extends AnyFunSuite:
  test("Circe parser test") {
    val json = """
    {
      "str": "Nobu",
      "num": 17,
      "list": ["tennis", "piano"],
      "bool": true,
      "null": null
    }
    """
    val result = CirceParser.parse(json).getOrElse(JsonNull)
    assert(result == JsonObject(Map(
      "str" -> JsonString("Nobu"),
      "num" -> JsonNumber(17),
      "list" -> JsonArray(Seq(JsonString("tennis"), JsonString("piano"))),
      "bool" -> JsonBoolean(true),
      "null" -> JsonNull,
    )))
  }