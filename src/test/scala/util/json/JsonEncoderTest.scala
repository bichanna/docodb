package com.bichanna.docodb
package util.json

import util.json.encoder.JsonEncoder

import util.*
import org.scalatest.funsuite.AnyFunSuite

class JsonEncoderTest extends AnyFunSuite:
  test("JsonEncoder class test") {
    case class SomeClass(str: String, num: Int)

    val encoder: JsonEncoder[SomeClass] = (c: SomeClass) => DocoMapping(Map(
      "str" -> DocoString(c.str),
      "num" -> DocoNumber(c.num)
    ))
    val instance = SomeClass("abc", 123)
    val result = encoder.encode(instance)

    assert(result == DocoMapping(Map("str" -> DocoString("abc"), "num" -> DocoNumber(123))))
  }
