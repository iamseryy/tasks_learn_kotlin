package org.example.util

import java.io.File

object FileUtils {
    @JvmStatic
    fun writeText(file: String, text: String) = File(file).writeText(text)
}