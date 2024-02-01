package org.example.command

import org.example.view.Console

class ShowCommand: Command {
    override fun execute(data: String?) = Console().output(Command.contacts.findLast().toString())
    override fun isValid(args: String?) = args.isNullOrEmpty()
}