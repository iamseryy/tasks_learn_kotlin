package org.example

import org.example.command.HelpCommand
import org.example.exception.*
import org.example.parser.CommandParser
import org.example.parser.CommandParserImpl
import org.example.view.Console
import org.example.view.UserInterface

fun main() {
    val console: UserInterface = Console()
    val commandParser: CommandParser = CommandParserImpl()
    while (true) {
        try {
            commandParser.readCommand(console.input()).run {
                if(!first.isValid(second)) HelpCommand().execute(first::class.simpleName) else first.execute(second)
            }
        } catch (e: ArgumentErrorException) {
            println(e.message)
            continue
        } catch (e: CommandErrorException) {
            println(e.message)
            continue
        } catch (e: FieldValidateErrorException) {
            println(e.message)
            continue
        } catch (e: NoPersonException) {
            println(e.message)
            continue
        } catch (e: ExitException) {
            println(e.message)
            break
        }
    }
}
