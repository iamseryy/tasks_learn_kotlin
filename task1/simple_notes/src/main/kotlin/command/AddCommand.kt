package org.example.command

import org.example.exception.ArgumentErrorException
import org.example.model.Entry

class AddCommand: Command<String, Boolean> {

    override fun execute(data: String?): Boolean {
        if(data.isNullOrEmpty()) throw ArgumentErrorException("Argument error")

        Command.contacts.add(Entry("XZC","ZXC", "ZCD"))
        return true
    }
}