package com.bichanna.docodb
package util.cache

import scala.collection.mutable.Map

/**
 * A trait for cache classes
 *
 * @tparam K The type of the keys
 * @tparam V The type of the values
 */
trait Cache[K, V] extends Map[K, V]:
  val capacity: Int