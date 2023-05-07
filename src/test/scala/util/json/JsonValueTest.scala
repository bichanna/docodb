package com.bichanna.docodb
package util.json

import org.scalatest.funsuite.AnyFunSuite

class JsonValueTest extends AnyFunSuite:
  test("JsonEncoder number test") {
    val json1 = JsonNumber(123).asJson
    val json2 = JsonNumber(123.4).asJson

    assert(json1 == "123")
    assert(json2 == "123.4")
  }

  test("JsonEncoder string test") {
    val json = JsonString("bichanna is cool!").asJson
    assert(json == "\"bichanna is cool!\"")
  }

  test("JsonEncoder boolean test") {
    val json = JsonBoolean(true).asJson
    assert(json == "true")
  }

  test("JsonEncoder null test") {
    val json = JsonNull.asJson
    assert(json == "null")
  }

  test("JsonEncoder array test") {
    val json = JsonArray(Seq(JsonNumber(1), JsonNumber(2))).asJson
    assert(json == "[1, 2]")
  }

  test("JsonEncoder object test") {
    val json = JsonObject(Map("a" -> JsonNumber(1), "b" -> JsonNumber(2))).asJson
    assert(json == """{"a" : 1, "b" : 2}""")
  }
