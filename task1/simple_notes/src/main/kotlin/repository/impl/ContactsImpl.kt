package org.example.repository.impl

import org.example.repository.Contacts
import org.example.model.Entry

class ContactsImpl: Contacts {
    private val entries = mutableListOf<Entry>()
    override fun add(entry: Entry) = entries.add(entry)
}