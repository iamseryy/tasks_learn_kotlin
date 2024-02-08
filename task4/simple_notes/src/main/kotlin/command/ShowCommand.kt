package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.repository.Contacts
import org.example.view.Console

class ShowCommand: Command {
    companion object {
        const val PERSON_NOT_FOUND = "Person not found"
    }
    override fun execute(contacts: Contacts, data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        with (contacts.findPersonByName(data.trim())) {
            Console().output(this?.toString() ?: PERSON_NOT_FOUND)
        }
    }

    override fun isValid(args: String?) = !args.isNullOrEmpty()
}