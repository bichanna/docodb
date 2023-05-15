package com.bichanna.docodb
package query

import util.DocoValue

import scala.util.matching.Regex
import scala.collection.mutable

sealed trait Operation

case class Path(op: Operation) extends Operation

case class Equal(op: Operation, rhs: Any) extends Operation

case class NotEqual(op: Operation, rhs: Any) extends Operation

case class Less(op: Operation, rhs: Any) extends Operation

case class Greater(op: Operation, rhs: Any) extends Operation

case class LessEqual(op: Operation, rhs: Any) extends Operation

case class GreaterEqual(op: Operation, rhs: Any) extends Operation

case class Exists(op: Operation) extends Operation

case class Matches(op: Operation, regex: Regex) extends Operation

case class Search(op: Operation, regex: Regex) extends Operation

case class CustomTest(op: Operation, func: mutable.Map[String, DocoValue] => Boolean) extends Operation

case class OneOf(op: Operation, items: Seq[Any]) extends Operation