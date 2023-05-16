package com.bichanna.docodb
package query

import util.DocoValue

import scala.collection.mutable
import scala.util.matching.Regex

sealed trait Operation:
  var arg: String
case class Nothing(var arg: String = "") extends Operation

case class And(self: Operation, op: Operation, var arg: String = "") extends Operation

case class Or(self: Operation, op: Operation, var arg: String = "") extends Operation

case class Not(op: Operation, var arg: String = "") extends Operation

case class Path(op: Operation, var arg: String = "") extends Operation

case class Equal(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class NotEqual(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class Less(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class Greater(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class LessEqual(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class GreaterEqual(op: Operation, rhs: Any, var arg: String = "") extends Operation

case class Exists(op: Operation, var arg: String = "") extends Operation

case class Matches(op: Operation, regex: Regex, var arg: String = "") extends Operation

case class Search(op: Operation, regex: Regex, var arg: String = "") extends Operation

case class CustomTest(op: Operation, func: mutable.Map[String, DocoValue] => Boolean, var arg: String = "") extends Operation

case class OneOf(op: Operation, items: Seq[Any], var arg: String = "") extends Operation