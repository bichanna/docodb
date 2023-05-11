package com.bichanna.docodb
package util.json

import util.*
import util.json.parser.CirceParser

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.Map

class JsonParserTest extends AnyFunSuite:
  test("Circe parser test") {
    val json =
      """
    {
      "str": "Nobu",
      "num": 17,
      "list": ["tennis", "piano"],
      "bool": true,
      "null": null
    }
    """
    val result = CirceParser.parse(json).getOrElse(DocoNull)
    assert(result == DocoMapping(Map(
      "str" -> DocoString("Nobu"),
      "num" -> DocoNumber(17),
      "list" -> DocoList(Seq(DocoString("tennis"), DocoString("piano"))),
      "bool" -> DocoBoolean(true),
      "null" -> DocoNull,
    )))
  }