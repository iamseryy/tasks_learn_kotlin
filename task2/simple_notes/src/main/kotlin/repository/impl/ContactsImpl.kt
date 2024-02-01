package org.example.repository.impl

import org.example.exception.NoPersonException
import org.example.repository.Contacts
import org.example.model.Person

class ContactsImpl: Contacts {

    companion object {
        const val NOT_INIT = "Not initialized"
    }

    private val people = mutableListOf<Person>()
    override fun add(person: Person) = people.add(person)
    override fun findLast(): Person {
        try {
           return people.last()
        } catch (e: NoSuchElementException) {
            throw NoPersonException(NOT_INIT)
        }

    }
}