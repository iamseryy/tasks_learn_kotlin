package org.example.command

import org.example.model.Entry

class User(private val add: Command, private val help: Command, private val exit: Command) {

    fun add(data: String) = add.execute(data)
    fun help() = help.execute()
    fun exit() = exit.execute()
}