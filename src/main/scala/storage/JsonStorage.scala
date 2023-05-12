package com.bichanna.docodb
package storage

import util.DocoValue
import util.json.asJsonValue
import util.json.parser.CirceParser

import java.io.*
import scala.util.{Failure, Success, Try}

/**
 * A concrete storage class for storing JSON
 *
 * @param path     Where to store the JSON data
 * @param mode     The mode in which the file is opened
 * @param encoding Which encoding format to use when opening the file
 */
class JsonStorage(path: String, mode: String = "rw", encoding: String = "UTF8") extends Storage:
  private val handle = RandomAccessFile(path, mode)

  override def read(): Option[DocoValue] =
    handle.seek(0)
    Try(handle.length()) match
      case Failure(_) => None
      case Success(size) =>
        // Read the whole file content
        val buffer = new Array[Byte](size.toInt)
        handle.readFully(buffer)
        val str = String(buffer, encoding)

        // Parse the string into DocoValue
        CirceParser.parse(str) match
          case Left(err) => None
          // Try converting DocoMapping
          case Right(docoValue) => Some(docoValue)

  override def write(value: DocoValue): Unit =
    // Move the cursor to the beginning of the file
    handle.seek(0)

    val serialized = value.asJson.getBytes(encoding)
    handle.write(serialized)

    // Ensure the file's been written
    handle.getFD.sync()

    // Remove the data behind the new cursor in case the file has gotten shorter
    handle.setLength(handle.getFilePointer)

  override def close(): Unit = handle.close()