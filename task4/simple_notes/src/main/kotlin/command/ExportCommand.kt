package org.example.command

import org.example.repository.Contacts

class ExportCommand: Command {
    override fun execute(contacts: Contacts, data: String?) {
        TODO("Not yet implemented")
    }

    override fun isValid(args: String?) = args.isNullOrEmpty()
}