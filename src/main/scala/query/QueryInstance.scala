package com.bichanna.docodb
package query

import util.DocoValue

import scala.collection.mutable

enum Operators:
  case And, Or, Not

case class HashTuple(op: Operators, left: Hash, right: Hash)

type Hash = Option[HashTuple]

class QueryInstance(test: mutable.Map[String, DocoValue] => Boolean, var hash: Hash):
  /**
   * Evaluates the query to check if it matches a specific value
   *
   * @param value The value to check
   * @return Whether the value matches the query
   */
  def apply(value: mutable.Map[String, DocoValue]): Boolean = test(value)

  override def toString: String = s"QueryImpl$hash"

  def and(other: QueryInstance): QueryInstance =
    val hashval = if isCacheable && other.isCacheable then
      Some(HashTuple(Operators.And, hash, other.hash)) else None
    QueryInstance(value => this (value) && other(value), hashval)

  def or(other: QueryInstance): QueryInstance =
    val hashval = if isCacheable && other.isCacheable then
      Some(HashTuple(Operators.Or, hash, other.hash)) else None
    QueryInstance(value => this (value) || other(value), hashval)

  def not: QueryInstance =
    val hashval = if isCacheable then Some(HashTuple(Operators.Not, hash, None)) else None
    QueryInstance(value => !this (value), hashval)

  def isCacheable: Boolean = hash.isDefined

  override def equals(obj: Any): Boolean =
    if obj.isInstanceOf[QueryInstance] then obj.hashCode() == hashCode() else false

  override def hashCode(): Int = hash.hashCode()