package org.example.repository.impl

import org.example.repository.Contacts
import org.example.model.Person

class ContactsImpl: Contacts {
    private val people = mutableListOf<Person>()
    override fun add(person: Person) = people.add(person)
    override fun findLast() = people.last()
}