package org.example.parser

import org.example.command.Command

interface Parser {
    fun parse(data: String?): () -> Unit
}