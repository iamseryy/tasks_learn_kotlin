package org.example.command

import org.example.view.Console

class HelpCommand: Command<Unit, Unit> {
    override fun execute(t: Unit?) = Console().output("HELP HELP HELP")
}