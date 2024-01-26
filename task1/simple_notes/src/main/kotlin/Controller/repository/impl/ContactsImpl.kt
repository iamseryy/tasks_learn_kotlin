package org.example.Controller.repository.impl

import org.example.Controller.repository.Contacts
import org.example.model.Entry

class ContactsImpl: Contacts {
    private val entries = mutableListOf<Entry>()

    override fun add(entry: Entry) = entries.add(entry)
}