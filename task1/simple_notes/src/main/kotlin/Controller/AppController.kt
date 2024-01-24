package org.example.Controller

import org.example.exception.ExitException
import org.example.view.Console
import org.example.view.UserInterface

class AppController {
    fun start() {
        val console: UserInterface = Console()
        while (true) {
            try {
                console.output(console.input())
            } catch (e: ExitException) {
                break
            }
        }
    }
}