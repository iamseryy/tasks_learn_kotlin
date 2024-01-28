package org.example.parser

import org.example.command.AddCommand
import org.example.command.Command
import org.example.command.ExitCommand
import org.example.command.HelpCommand
import org.example.exception.CommandErrorException
import org.example.model.Entry
import org.example.repository.Contacts

class ParserImpl: Parser {
    override fun parse(data: String?): () -> Unit {
        if(data.isNullOrEmpty()) throw CommandErrorException("Command error")
        val items = data.split(" ")
        val command = items.first()
        val args = items.subList(1, items.size).joinToString(" ")

        return {AddCommand().execute(args)}
    }
}