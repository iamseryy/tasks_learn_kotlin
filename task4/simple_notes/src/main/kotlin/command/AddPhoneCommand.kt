package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.repository.Contacts
import org.example.util.Validator

class AddPhoneCommand: Command {
    private val parser = Parser()
    override fun execute(contacts: Contacts, data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        parser.parse(data).let { contacts.addPhone(it.first, it.second) }
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