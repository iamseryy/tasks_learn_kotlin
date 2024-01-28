package org.example.command

import org.example.model.Entry

class User(private val add: Command<String, Boolean>, private val help: Command<Unit, Unit>, private val exit: Command<Unit, Unit>) {

    fun add(data: String): Boolean {
        return add.execute(data)
    }
    fun help() = help.execute()
    fun exit() = exit.execute()
}