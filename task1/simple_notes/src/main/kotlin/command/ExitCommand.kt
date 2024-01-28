package org.example.command

import org.example.exception.ExitException

class ExitCommand: Command<Unit, Unit> {
    override fun execute(t: Unit?) {
        throw ExitException("The application is closed")
    }
}