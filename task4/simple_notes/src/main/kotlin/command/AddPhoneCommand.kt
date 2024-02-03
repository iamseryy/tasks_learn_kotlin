package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.model.Person
import org.example.util.Validator
import org.example.view.Console

class AddPhoneCommand: Command {
    private val parser = Parser()
    override fun execute(data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        parser.parse(data).let {
            Person.EntryBuilder()
                .name(it.first)
                .phoneNumber(it.second)
                .build()
        }.also {
            if(Command.contacts.isPersonExists(it)) Command.contacts.update(it) else Command.contacts.add(it)
            Console().output(Command.contacts.findPersonByName(it.name).toString())
        }
    }

    override fun isValid(args: String?) = try {
        parser.parse(args)
        true
    } catch (e: ArgumentErrorException) {
        false
    }

    private inner class Parser {
        fun parse(data: String?): Pair<String, String> {
            if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            val parts = data.split(" ")
            if(parts.size != 2) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if(!Validator.isPhoneNumberValid(parts[1])) throw PhoneNumberValidateErrorException(Validator.PHONE_NUMBER_ERROR)
            return parts[0] to parts[1]
        }
    }
}