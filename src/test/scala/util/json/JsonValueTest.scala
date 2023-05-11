package com.bichanna.docodb
package util.json

import util.*
import util.json.asJsonValue

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.Map

class JsonValueTest extends AnyFunSuite:
  test("JsonEncoder number test") {
    val json1 = DocoNumber(123).asJson
    val json2 = DocoNumber(123.4).asJson

    assert(json1 == "123")
    assert(json2 == "123.4")
  }

  test("JsonEncoder string test") {
    val json = DocoString("bichanna is cool!").asJson
    assert(json == "\"bichanna is cool!\"")
  }

  test("JsonEncoder boolean test") {
    val json = DocoBoolean(true).asJson
    assert(json == "true")
  }

  test("JsonEncoder null test") {
    val json = DocoNull.asJson
    assert(json == "null")
  }

  test("JsonEncoder array test") {
    val json = DocoList(Seq(DocoNumber(1), DocoNumber(2))).asJson
    assert(json == "[1, 2]")
  }

  test("JsonEncoder object test") {
    val json = DocoMapping(Map("a" -> DocoNumber(1), "b" -> DocoNumber(2))).asJson
    assert(json == """{"a": 1, "b": 2}""")
  }