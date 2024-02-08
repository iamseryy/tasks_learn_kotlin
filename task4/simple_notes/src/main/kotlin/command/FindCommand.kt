package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.model.Person
import org.example.repository.Contacts
import org.example.util.Validator
import org.example.view.Console


class FindCommand: Command {
    private val parser = Parser()
    companion object {
        const val NOTHING_FOUND = "Nothing found"
    }

    override fun isValid(args: String?) = !args.isNullOrEmpty()

    override fun execute(contacts: Contacts, data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        val subCommands: List<(String) -> HashSet<Person>> = listOf(
            { contacts.findPeopleByPhone(it) },
            { contacts.findPeopleByEmail(it) })

        parser.getCommand(subCommands, data).also {
            if (it == null) {
               Console().output(NOTHING_FOUND)
               return
            }

            val (executor, value) = it
            val people = executor(value)
            Console().output(if(people.isNotEmpty()) people.toString() else NOTHING_FOUND)
        }
    }

    private inner class Parser {
        fun getCommand(subCommands: List<(String) -> HashSet<Person>>, data: String) =  when {
                Validator.isPhoneNumberValid(data) -> subCommands[0] to data
                Validator.isEmailValid(data) -> subCommands[1] to data
                else -> null
            }
    }
}