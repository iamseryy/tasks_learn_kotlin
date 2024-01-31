package org.example.command

import org.example.exception.ExitException

class ExitCommand: Command {
    override fun isValid(args: String?): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        const val EXIT_MESSAGE = "Argument error"
    }
    override fun execute(data: String?) = throw ExitException(EXIT_MESSAGE)
}