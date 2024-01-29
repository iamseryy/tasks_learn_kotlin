package org.example.command

import org.example.exception.ExitException

class ExitCommand: Command {
    companion object {
        const val EXIT_MESSAGE = "Argument error"
    }
    override fun execute(data: String?) = throw ExitException(EXIT_MESSAGE)
}