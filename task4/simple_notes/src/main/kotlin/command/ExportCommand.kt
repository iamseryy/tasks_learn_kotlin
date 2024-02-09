package org.example.command

import org.example.dsl.json
import org.example.exception.ArgumentErrorException
import org.example.exception.NoPersonException
import org.example.repository.Contacts
import org.example.util.FileUtils
import org.example.view.Console


class ExportCommand: Command {
    companion object {
        const val EXPORT_COMPLETED = "export completed"
    }

    override fun execute(contacts: Contacts, data: String?) {
        if (!isValid(data)) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
        if (contacts.isEmpty()) throw NoPersonException(Contacts.NOT_INITIALIZED)

        json {
            jlist {
                contacts.findAll().forEach{
                    jobj {
                        p("name" to it.name)
                        p("phones" to it.phones)
                        p("emails" to it.emails)
                    }
                }
            }
        }.run { FileUtils.writeText(data!!, this.toString())
            Console().output(EXPORT_COMPLETED)
        }
    }

    override fun isValid(args: String?): Boolean {
        args.isNullOrEmpty()

        return true
    }
}