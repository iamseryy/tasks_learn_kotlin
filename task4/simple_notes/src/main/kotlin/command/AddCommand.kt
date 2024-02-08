package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.EmailValidateErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.model.Person
import org.example.repository.Contacts
import org.example.util.Validator
import org.example.view.Console

class AddCommand: Command {
   //private var args = HashMap<String, String>()
    private val parser = Parser()

    override fun execute(contacts: Contacts, data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

//        if(args.isEmpty()) parser.parse(data)
//
//        Command.contacts.add(args["name"] ?: "", args["email"] ?: "") {
//            name: String, email: String -> contacts.addEmail(name, email)
//        }
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
            val argsForValidate = hashSetOf< Triple<String, () -> Exception, (String) -> Boolean> > (
                Triple("phone", { throw PhoneNumberValidateErrorException(Validator.PHONE_NUMBER_ERROR) }) { Validator.isPhoneNumberValid(it) },
                Triple("email", { throw EmailValidateErrorException(Validator.EMAIL_ERROR) }) { Validator.isEmailValid(it) }
            )

        fun parse(data: String): HashSet<Triple<String, String, (String, String) -> Unit>> {
            val items = data.trim().split(" ")

            if (items.isEmpty() || items.size > argsForValidate.size + 3) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (argsForValidate.any { it.first == items[0] }) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (items.first().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (items.drop(1).count() % 2 != 0) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

            val args = HashSet<Triple<String, String, (String, String) -> Unit>>()
            val name = items[0]

            items.drop(1)
                .chunked(2)
                .forEach { it ->
                    if(argsForValidate.none { arg -> arg.first == it[0] } || args.map { it.second }.contains(it[0])) {
                        throw ArgumentErrorException(Command.ARGUMENT_ERROR)
                    } else {
                        if(argsForValidate.first { arg -> arg.first == it[0] }.third(it[1])) {
//                            args.add(Triple(name, it[0], ))
                        } else argsForValidate.first { arg -> arg.first == it[0] }.second
                    }
                }
            return args

        }
    }
}