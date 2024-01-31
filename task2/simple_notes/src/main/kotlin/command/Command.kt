package org.example.command

import org.example.repository.Contacts
import org.example.repository.impl.ContactsImpl

sealed interface Command {
    companion object {
        val contacts: Contacts = ContactsImpl()
        val commands: HashMap<String, Command> = hashMapOf(
            "add" to AddCommand(),
            "show" to ShowCommand(),
            "help" to HelpCommand(),
            "exit" to ExitCommand())
    }

    fun execute(data: String? = null)
    fun isValid(args: String?): Boolean
}