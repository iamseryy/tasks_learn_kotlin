package org.example.view

class Console: UserInterface {
    override fun input() = readln()

    override fun output(date: String) {
        println(date)
    }
}