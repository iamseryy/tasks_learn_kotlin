package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.model.Entry
import org.example.view.Console

class AddCommand: Command {
    companion object {
        const val ARGUMENT_ERROR = "Argument error"
        val args = hashSetOf("phone", "email")
     }
    override fun execute(data: String?) {
        if(data.isNullOrEmpty() || data.trim().isNullOrEmpty()) throw ArgumentErrorException(ARGUMENT_ERROR)
        val fields = Parser().parse(data)

        val entry = Entry.EntryBuilder()
            .name(fields["name"] ?: throw ArgumentErrorException(ARGUMENT_ERROR))
            .phoneNumber(fields["phone"] ?: "")
            .email(fields["email"] ?: "")
            .build()

        if (!entry.isValid())

        Command.contacts.add(entry)
        Console().output("Name: ${entry.name}" +
                "${if(!entry.phoneNumber.isNullOrEmpty()) "; phone number: " + entry.phoneNumber else ""}" +
                "${if(!entry.email.isNullOrEmpty()) "; email: " + entry.email else ""}")
    }

    private class Parser {
        fun parse(data: String): HashMap<String, String> {
            val items = data.trim().split(" ")
            if (items.isEmpty() || items.size > args.size + 3) throw ArgumentErrorException(ARGUMENT_ERROR)

            if (args.contains(items[0])) throw ArgumentErrorException(ARGUMENT_ERROR)
            val fields = hashMapOf("name" to items[0])

            if(items.drop(1).count() % 2 != 0) throw ArgumentErrorException(ARGUMENT_ERROR)

            items
                .drop(1)
                .chunked(2)
                .forEach { if(!args.contains(it[0]) || fields.contains(it[0]))
                    throw ArgumentErrorException(ARGUMENT_ERROR) else fields[it[0]] = it[1] }

            return fields
        }


    }
}