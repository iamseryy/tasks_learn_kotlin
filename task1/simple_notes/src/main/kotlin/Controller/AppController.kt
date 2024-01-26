package org.example.Controller

import org.example.Controller.command.AddCommand
import org.example.Controller.command.ExitCommand
import org.example.Controller.command.HelpCommand
import org.example.exception.ExitException
import org.example.model.User
import org.example.view.Console
import org.example.view.UserInterface

class AppController {
    fun start() {
        val console: UserInterface = Console()
        val user = User(AddCommand(), HelpCommand(), ExitCommand())
        while (true) {
            try {
                console.output(console.input())
            } catch (e: ExitException) {
                break
            }
        }
    }
}