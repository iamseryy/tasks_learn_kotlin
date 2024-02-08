package org.example.parser

import org.example.command.Command

interface CommandParser {
    fun readCommand(data: String?): Pair<() -> Command, String?>
}