package org.example.Controller

import org.example.command.*
import org.example.exception.ArgumentErrorException
import org.example.exception.CommandErrorException
import org.example.exception.ExitException
import org.example.parser.Parser
import org.example.parser.ParserImpl
import org.example.view.Console
import org.example.view.UserInterface

class AppController {
    fun start() {
        val console: UserInterface = Console()
        val user = User(AddCommand(), HelpCommand(), ExitCommand())
        val parser: Parser = ParserImpl()
        while (true) {
            try {
                val commandExecute = parser.parse(console.input())
                commandExecute()
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