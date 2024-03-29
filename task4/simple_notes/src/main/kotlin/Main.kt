package org.example

import org.example.command.HelpCommand
import org.example.exception.*
import org.example.parser.CommandParser
import org.example.parser.CommandParserImpl
import org.example.repository.Contacts
import org.example.repository.impl.ContactsImpl
import org.example.view.Console
import org.example.view.UserInterface


fun main() {
    val console: UserInterface = Console()
    val commandParser: CommandParser = CommandParserImpl()
    val contacts: Contacts = ContactsImpl()
    while (true) {
        try {
            commandParser.readCommand(console.input()).run {
                if(!first().isValid(second)) HelpCommand().execute(contacts, first()::class.simpleName) else first().execute(contacts, second)
            }
        } catch (e: ArgumentErrorException) {
            console.output(e.message)
            continue
        } catch (e: CommandErrorException) {
            console.output(e.message)
            continue
        } catch (e: FieldValidateErrorException) {
            console.output(e.message)
            continue
        } catch (e: NoPersonException) {
            console.output(e.message)
            continue
        } catch (e: ExitException) {
            console.output(e.message)
            break
        }
    }
}
