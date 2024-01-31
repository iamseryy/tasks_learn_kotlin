package org.example.command

import org.example.view.Console

class HelpCommand: Command {
    override fun isValid(args: String?) = !args.isNullOrEmpty()

    companion object {
        const val HELP_ALL = "help content for everything"
        const val HELP_ADD = "help content for 'add' command"
        const val HELP_SHOW = "help content for 'show' command"
        const val HELP_EXIT = "help content for 'exit' command"

    }
    override fun execute(data: String?) {
        Console().output(when (data) {
            "AddCommand" -> HELP_ADD
            "ShowCommand" -> HELP_SHOW
            "HelpCommand" -> HELP_ALL
            "ExitCommand" -> HELP_EXIT
            else -> HELP_ALL
        })
    }
}