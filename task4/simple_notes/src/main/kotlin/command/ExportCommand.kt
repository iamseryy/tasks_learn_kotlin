package org.example.command

import org.example.dsl.json
import org.example.exception.ArgumentErrorException
import org.example.exception.NoPersonException
import org.example.repository.Contacts
import org.example.util.FileUtils
import org.example.view.Console
import java.io.FileNotFoundException


class ExportCommand: Command {
    companion object {
        const val EXPORT_COMPLETED = "export completed"
    }

    override fun isValid(args: String?): Boolean {
        if (args.isNullOrEmpty() || args.trim().isEmpty()) return false
        return args?.trim()?.split(" ")?.size == 1
    }

    override fun execute(contacts: Contacts, data: String?) {
        if (!isValid(data)) throw ArgumentErrorException(Command.ARGUMENT_ERROR)
        if (contacts.isEmpty()) throw NoPersonException(Contacts.NOT_INITIALIZED)

        getExportData(contacts).run {
            try {
                FileUtils.writeText(data!!, this.toString())
                Console().output(EXPORT_COMPLETED)
            } catch (e: FileNotFoundException) {
                throw ArgumentErrorException(e.stackTraceToString())
            }
        }
    }

    private fun getExportData(contacts: Contacts) = json {
        jlist {
            contacts.findAll().forEach {
                jobj {
                    p("name" to it.name)
                    p("phones" to it.phones)
                    p("emails" to it.emails)
                }
            }
        }
    }
}