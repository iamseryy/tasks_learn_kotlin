package org.example.view

class Console: UserInterface {
    override fun input(): String? = print("> ").run { readlnOrNull() }
    override fun output(date: String) = println(date)
}