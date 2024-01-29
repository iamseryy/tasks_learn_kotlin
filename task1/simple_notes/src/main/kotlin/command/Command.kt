package org.example.command

import org.example.repository.Contacts
import org.example.repository.impl.ContactsImpl

interface Command {
    companion object {
        val  contacts: Contacts = ContactsImpl()
        val commands: HashMap<String, Command> = hashMapOf("add" to AddCommand(), "help" to HelpCommand(), "exit" to ExitCommand())
    }
    fun execute(data: String? = null)
}