package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.EmailValidateErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.model.Person
import org.example.util.Validator
import org.example.view.Console

class AddCommand: Command {
    private var args = HashMap<String, String>()
    private val parser = Parser()

    override fun execute(data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

        if(args.isEmpty()) parser.parse(data)

        with(
            Person.EntryBuilder()
                .name(args["name"] ?: throw ArgumentErrorException(Command.ARGUMENT_ERROR))
                .phoneNumber(args["phone"] ?: "")
                .email(args["email"] ?: "")
                .build()
        ) {
            if(Command.contacts.isPersonExists(this)) Command.contacts.update(this) else Command.contacts.add(this)
            Console().output(Command.contacts.findPersonByName(this.name).toString())
        }
    }

    override fun isValid(data: String?): Boolean {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) return false

        return try {
            parser.parse(data)
            true
        } catch (e: ArgumentErrorException) {
            false
        }
    }

    private inner class Parser {
            val argsForValidate = hashSetOf< Triple<String, Exception, (String) -> Boolean> > (
                Triple("phone", PhoneNumberValidateErrorException(Validator.PHONE_NUMBER_ERROR)) { Validator.isPhoneNumberValid(it) },
                Triple("email", EmailValidateErrorException(Validator.EMAIL_ERROR)) { Validator.isEmailValid(it) }
            )

        fun parse(data: String) {
            args.clear()
            val items = data.trim().split(" ")

            if (items.isEmpty() || items.size > argsForValidate.size + 3) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (argsForValidate.any { it.first == items[0] }) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if (items.first().isEmpty()) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
            if(items.drop(1).count() % 2 != 0) throw ArgumentErrorException(Command.ARGUMENT_ERROR)

            args["name"] = items.first()
            items.drop(1)
                .chunked(2)
                .forEach {
                    if(argsForValidate.none { arg -> arg.first == it[0] } || args.contains(it[0])) {
                        throw ArgumentErrorException(Command.ARGUMENT_ERROR)
                    } else {
                        if(argsForValidate.first { arg -> arg.first == it[0] }.third(it[1])) {
                            args[it[0]] = it[1]
                        } else throw argsForValidate.first { arg -> arg.first == it[0] }.second
                    }
                }
        }
    }
}