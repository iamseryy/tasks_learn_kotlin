package org.example.command

import org.example.exception.ExitException

class ExitCommand: Command {
    override fun isValid(args: String?) = args.isNullOrEmpty()

    companion object {
        const val EXIT_MESSAGE = "Application closed"
    }
    override fun execute(data: String?) = throw ExitException(EXIT_MESSAGE)
}