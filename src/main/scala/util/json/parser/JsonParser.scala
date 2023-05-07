package com.bichanna.docodb
package util.json.parser

import util.json.JsonValue

trait JsonParser:
  def parse(json: String): Either[Throwable, JsonValue]