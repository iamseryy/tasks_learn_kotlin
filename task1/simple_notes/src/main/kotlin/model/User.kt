package org.example.model

import org.example.Controller.command.Command
import org.example.Controller.repository.Contacts

class User(private val add: Command, private val help: Command, private val exit: Command) {
    private lateinit var contacts: Contacts
    fun add() = add.execute()
    fun help() = help.execute()
    fun exit() = exit.execute()
}