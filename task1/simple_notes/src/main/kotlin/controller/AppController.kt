package org.example.Controller

import org.example.exception.ArgumentErrorException
import org.example.exception.CommandErrorException
import org.example.exception.ExitException
import org.example.parser.CommandParser
import org.example.parser.CommandParserImpl
import org.example.view.Console
import org.example.view.UserInterface

class AppController {
    fun start() {
        val console: UserInterface = Console()
        val commandParser: CommandParser = CommandParserImpl()
        while (true) {
            try {
                commandParser.parse(console.input()).run { first.execute(second) }
            } catch (e: ExitException) {
                println(e.message)
                break
            } catch (e: ArgumentErrorException) {
                println(e.message)
                continue
            } catch (e: CommandErrorException) {
                println(e.message)
                continue
            }
        }
    }
}