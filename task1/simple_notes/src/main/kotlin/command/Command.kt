package org.example.command

import org.example.model.Entry
import org.example.repository.Contacts
import org.example.repository.impl.ContactsImpl

interface Command <T, S> {
    companion object {
        var contacts: Contacts = ContactsImpl()
        val commands = setOf("add" to AddCommand(), "help" to HelpCommand(), "exit" to ExitCommand())
    }
    fun execute(t: T? = null): S
}