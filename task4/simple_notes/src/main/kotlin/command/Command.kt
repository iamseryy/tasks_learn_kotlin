package org.example.command

import org.example.repository.Contacts
import org.example.repository.impl.ContactsImpl

sealed interface Command {
    companion object {
        val commands: HashMap<String, () -> Command> = hashMapOf(
            "add" to {AddCommand()},
            "AddPhone" to {AddPhoneCommand()},
            "AddEmail" to {AddEmailCommand()},
            "show" to {ShowCommand()},
            "find" to {FindCommand()},
            "help" to {HelpCommand()},
            "export" to {ExportCommand()},
            "exit" to {ExitCommand()})

        const val ARGUMENT_ERROR = "Argument error"
    }

    fun execute(contacts: Contacts, data: String? = null)
    fun isValid(args: String?): Boolean
}