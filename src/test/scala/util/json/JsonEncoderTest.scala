package com.bichanna.docodb
package util.json

import util.json.encoder.JsonEncoder

import org.scalatest.funsuite.AnyFunSuite

class JsonEncoderTest extends AnyFunSuite:
  test("JsonEncoder class test") {
    case class SomeClass(str: String, num: Int)

    val encoder: JsonEncoder[SomeClass] = (c: SomeClass) => JsonObject(Map(
      "str" -> JsonString(c.str),
      "num" -> JsonNumber(c.num)
    ))
    val instance = SomeClass("abc", 123)
    val result = encoder.encode(instance)

    assert(result == JsonObject(Map("str" -> JsonString("abc"), "num" -> JsonNumber(123))))
    assert(result.asJson == """{"str" : "abc", "num" : 123}""")
  }
