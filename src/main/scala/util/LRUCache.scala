package com.bichanna.docodb
package util

import java.util.NoSuchElementException
import scala.collection.mutable

class LRUCache[K, V](private val capacity: Int) extends mutable.Map[K, V]:
  private var cache = mutable.LinkedHashMap.empty[K, V]

  def lru: Seq[K] = cache.keys.toSeq

  @throws(classOf[NoSuchElementException])
  override def apply(key: K): V = cache.get(key) match
    case Some(value) => value
    case None => throw NoSuchElementException()

  override def getOrElse[V1 >: V](key: K, default: => V1): V1 = get(key) match
    case Some(value) => value
    case None => default

  override def get(key: K): Option[V] = cache.get(key) match
    case Some(value) =>
      cache.remove(key)
      cache += (key -> value)
      Some(value)
    case None => None

  override def iterator: Iterator[(K, V)] = cache.iterator

  override def subtractOne(elem: K): LRUCache.this.type =
    cache.subtractOne(elem)
    this

  override def update(key: K, value: V): Unit = addOne((key, value))

  override def addOne(elem: (K, V)): LRUCache.this.type =
    if cache.contains(elem._1) then
      cache.remove(elem._1)
      cache += elem
    else
      cache += elem
      if length > capacity then
        cache = cache.tail
    this

  def length: Int = cache.size

  override def clear(): Unit = cache.clear()

  override def contains(key: K): Boolean = cache.contains(key)

  override def remove(key: K): Option[V] = cache.remove(key)

  override def isDefinedAt(key: K): Boolean = cache.isDefinedAt(key)

  override def keys: Iterable[K] = cache.keys

  override def keySet: collection.Set[K] = cache.keySet

  override def keysIterator: Iterator[K] = cache.keysIterator

  override def values: Iterable[V] = cache.values

  override def valuesIterator: Iterator[V] = cache.valuesIterator