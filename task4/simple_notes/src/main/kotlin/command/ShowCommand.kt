package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.view.Console

class ShowCommand: Command {
    companion object {
        const val PERSON_NOT_FOUND = "Person not found"
    }
    override fun execute(name: String?) {
        if(name.isNullOrEmpty() || name.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        with (Command.contacts.findPersonByName(name.trim())) {
            Console().output(this?.toString() ?: PERSON_NOT_FOUND)
        }
    }

    override fun isValid(args: String?) = !args.isNullOrEmpty()
}