package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.EmailValidateErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.model.Person
import org.example.view.Console

class AddCommand: Command {
    private var args = HashMap<String, String>()
    private val parser = Parser()

    companion object {
        const val ARGUMENT_ERROR = "Argument error"
        const val PHONE_NUMBER_ERROR = "Phone number error"
        const val EMAIL_ERROR = "Email error"
     }

    override fun execute(data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(ARGUMENT_ERROR)


        if(args.isEmpty()) parser.parse(data)

        val person = Person.EntryBuilder()
            .name(args["name"] ?: throw ArgumentErrorException(ARGUMENT_ERROR))
            .phoneNumber(args["phone"] ?: "")
            .email(args["email"] ?: "")
            .build()

        Command.contacts.add(person)
        Console().output(person.toString())
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
                Triple("phone", PhoneNumberValidateErrorException(PHONE_NUMBER_ERROR)) { isPhoneNumberValid(it) },
                Triple("email", EmailValidateErrorException(EMAIL_ERROR)) { isEmailValid(it) }
            )

        fun parse(data: String) {
            args.clear()
            val items = data.trim().split(" ")

            if (items.isEmpty() || items.size > argsForValidate.size + 3) throw ArgumentErrorException(ARGUMENT_ERROR)
            if (argsForValidate.any { it.first == items[0] }) throw ArgumentErrorException(ARGUMENT_ERROR)
            if (items.first().isEmpty()) throw ArgumentErrorException(ARGUMENT_ERROR)
            if(items.drop(1).count() % 2 != 0) throw ArgumentErrorException(ARGUMENT_ERROR)

            args["name"] = items.first()
            items.drop(1)
                .chunked(2)
                .forEach {
                    if(argsForValidate.none { arg -> arg.first == it[0] } || args.contains(it[0])) {
                        throw ArgumentErrorException(ARGUMENT_ERROR)
                    } else {
                        if(argsForValidate.first { arg -> arg.first == it[0] }.third(it[1])) {
                            args[it[0]] = it[1]
                        } else throw argsForValidate.first { arg -> arg.first == it[0] }.second
                    }
                }
        }

        fun isPhoneNumberValid(phoneNumber: String) = phoneNumber.matches(Regex("""^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}${'$'}"""))
        fun isEmailValid(email: String) = email.matches(Regex("""(?!(^[.-].*|[^@]*\.@|.*\.{2,}.*)|^.{254}.)([a-zA-Z0-9!#${'$'}%&'*+\/=?^_`{|}~.-]+@)(?!-.*|.*-\.)([a-zA-Z0-9-]{1,63}\.)+[a-zA-Z]{2,15}"""))
    }
}