package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.EmailValidateErrorException
import org.example.exception.PhoneValidateErrorException
import org.example.repository.Contacts
import org.example.util.Validator

class AddCommand: Command {
    private val parser = Parser()

    override fun execute(contacts: Contacts, data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        parser.parse(data).forEach {
            when(it.key.second) {
                "email" -> contacts.addEmail(it.key.first, it.value)
                "phone" -> contacts.addPhone(it.key.first, it.value)
            }
        }
    }

    override fun isValid(arg: String?): Boolean {
        if(arg.isNullOrEmpty() || arg.trim().isEmpty()) return false

        return try {
            parser.parse(arg)
            true
        } catch (e: ArgumentErrorException) {
            false
        }
    }

    private inner class Parser {
            val argsForValidate = hashMapOf< String, Pair <() -> Exception, (String) -> Boolean >> (
                "phone" to Pair( { throw PhoneValidateErrorException(Validator.PHONE_NUMBER_ERROR) }, { Validator.isPhoneNumberValid(it) }) ,
                "email" to Pair( { throw EmailValidateErrorException(Validator.EMAIL_ERROR) }, { Validator.isEmailValid(it) })
            )

        fun parse(data: String): HashMap<Pair<String, String>, String> {
            val items = data.trim().split(" ")

            if (items.size < 3 || items.size > argsForValidate.size + 3) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (argsForValidate.containsKey(items[0])) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (items.first().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (items.drop(1).count() % 2 != 0) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

            val args = HashMap<Pair<String, String>, String>()
            val name = items[0]

            items.drop(1)
                .chunked(2)
                .forEach {
                    if(!argsForValidate.containsKey(it[0]) || args.containsKey(name to it[0])) {
                        throw ArgumentErrorException(Command.ARGUMENT_ERROR)
                    } else {
                        if(argsForValidate[it[0]]?.second!!.invoke(it[1])) {
                            args[name to it[0]] = it[1]
                        } else throw argsForValidate[it[0]]?.first!!.invoke()
                    }
                }
            return args
        }
    }
}