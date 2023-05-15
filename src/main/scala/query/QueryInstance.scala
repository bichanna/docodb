package com.bichanna.docodb
package query

import util.DocoValue

import scala.collection.mutable

class QueryInstance(protected var test: mutable.Map[String, DocoValue] => Boolean, protected var hash: Operation):
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
      And(hash, other.hash) else Nothing
    QueryInstance(value => this (value) && other(value), hashval)

  def isCacheable: Boolean = hash != Nothing

  def or(other: QueryInstance): QueryInstance =
    val hashval = if isCacheable && other.isCacheable then
      Or(hash, other.hash) else Nothing
    QueryInstance(value => this (value) || other(value), hashval)

  def not: QueryInstance =
    val hashval = if isCacheable then Not(hash) else Nothing
    QueryInstance(value => !this (value), hashval)

  override def equals(obj: Any): Boolean =
    if obj.isInstanceOf[QueryInstance] then obj.hashCode() == hashCode() else false

  override def hashCode(): Int = hash.hashCode()