package com.bichanna.docodb
package util.json.parser

import util.DocoValue

/**
 * A trait for parsing JSON strings to an AST
 */
trait JsonParser:

  /**
   * Parses the given JSON string to a `JsonValue`
   * @param json
   * @return The parsed `DocoValue` or `Throwable`
   */
  def parse(json: String): Either[Throwable, DocoValue]