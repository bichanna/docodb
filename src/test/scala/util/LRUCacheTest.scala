package com.bichanna.docodb
package util

import util.LRUCache

import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Failure, Try}

class LRUCacheTest extends AnyFunSuite:

  test("LRUCache multiple set test") {
    val cache = LRUCache[Char, Int](capacity = 3)
    cache('a') = 1
    cache('a') = 2
    cache('a') = 3

    assert(cache.lru == Seq('a'))
  }

  test("LRUCache multiple get test") {
    val cache = LRUCache[Char, Int](capacity = 3)
    cache('a') = 1
    cache('b') = 2
    cache('c') = 3
    cache.get('a')
    cache('d') = 4

    assert(cache.lru == Seq('c', 'a', 'd'))
  }

  test("LRUCache delete test") {
    val cache = LRUCache[Char, Int](capacity = 3)
    cache('a') = 1
    cache('b') = 2
    cache.remove('a')

    assert(cache.remove('i') == None)
    assert(cache.lru == Seq('b'))
  }

  test("LRUCache clear test") {
    val cache = LRUCache[Char, Int](capacity = 2)
    cache('a') = 1
    cache('b') = 2
    cache.clear()

    assert(cache.lru == Seq.empty)
  }

  test("LRUCache (pseudo) unbounded test") {
    val cache = LRUCache[Int, Int](capacity = Int.MaxValue)
    for i <- 1 to 1000 do cache(i) = i

    assert(cache.length == 1000)
  }

  test("LRUCache iteration test") {
    val cache = LRUCache[Int, Int](capacity = 10)
    for i <- 1 to 10 do cache(i) = i

    for (k, v) <- cache do
      assert(k == v)
  }