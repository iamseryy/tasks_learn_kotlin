package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.exception.EmailValidateErrorException
import org.example.exception.PhoneNumberValidateErrorException
import org.example.model.Entry
import org.example.view.Console

class AddCommand: Command {
    companion object {
        const val ARGUMENT_ERROR = "Argument error"
        const val PHONE_NUMBER_ERROR = "Phone number error"
        const val EMAIL_ERROR = "Email error"
        val argsForValidate = hashSetOf< Triple<String, Exception, (String) -> Boolean> > (
            Triple("phone", PhoneNumberValidateErrorException(PHONE_NUMBER_ERROR)) { Parser().isPhoneNumberValid(it) },
            Triple("email", EmailValidateErrorException(EMAIL_ERROR)) { Parser().isEmailValid(it) }
        )
     }
    override fun execute(data: String?) {
        if(data.isNullOrEmpty() || data.trim().isEmpty()) throw ArgumentErrorException(ARGUMENT_ERROR)

        val fields = Parser().parse(data)

        val entry = Entry.EntryBuilder()
            .name(fields["name"] ?: throw ArgumentErrorException(ARGUMENT_ERROR))
            .phoneNumber(fields["phone"] ?: "")
            .email(fields["email"] ?: "")
            .build()

        Command.contacts.add(entry)
        Console().output("Name: ${entry.name}" +
                "${if(!entry.phoneNumber.isNullOrEmpty()) "; phone number: " + entry.phoneNumber else ""}" +
                "${if(!entry.email.isNullOrEmpty()) "; email: " + entry.email else ""}")
    }

    private class Parser {
        fun parse(data: String): HashMap<String, String> {
            val items = data.trim().split(" ")
            if (items.isEmpty() || items.size > argsForValidate.size + 3) throw ArgumentErrorException(ARGUMENT_ERROR)
            if (argsForValidate.any { it.first == items[0] }) throw ArgumentErrorException(ARGUMENT_ERROR)
            val fields = hashMapOf("name" to items[0])
            if(items.drop(1).count() % 2 != 0) throw ArgumentErrorException(ARGUMENT_ERROR)

            items.drop(1)
                .chunked(2)
                .forEach {
                    if(argsForValidate.none { arg -> arg.first == it[0] } || fields.contains(it[0])) {
                        throw ArgumentErrorException(ARGUMENT_ERROR)
                    } else {
                        if(argsForValidate.first { arg -> arg.first == it[0] }.third(it[1])) {
                            fields[it[0]] = it[1]
                        } else throw argsForValidate.first { arg -> arg.first == it[0] }.second
                    }
                }

            return fields
        }

        fun isPhoneNumberValid(phoneNumber: String) = phoneNumber.matches(Regex("""^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}${'$'}"""))
        fun isEmailValid(email: String) = email.matches(Regex("""(?!(^[.-].*|[^@]*\.@|.*\.{2,}.*)|^.{254}.)([a-zA-Z0-9!#${'$'}%&'*+\/=?^_`{|}~.-]+@)(?!-.*|.*-\.)([a-zA-Z0-9-]{1,63}\.)+[a-zA-Z]{2,15}"""))
    }
}