package org.example.repository

import org.example.model.Person

interface Contacts {
    fun add(person: Person): Boolean
    fun findPersonByName(name: String): Person?
    fun isPersonExists(person: Person): Boolean
    fun update(person: Person)
    fun findPeopleByEmail(email: String): HashSet<Person>
    fun findPeopleByPhone(phone: String): HashSet<Person>
}