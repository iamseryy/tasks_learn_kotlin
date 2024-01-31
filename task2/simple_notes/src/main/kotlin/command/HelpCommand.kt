package org.example.command

import org.example.view.Console

class HelpCommand: Command {
    companion object {
        const val HELP_CONTENT = "HELP HELP HELP"
    }
    override fun execute(data: String?) = Console().output(HELP_CONTENT)
}