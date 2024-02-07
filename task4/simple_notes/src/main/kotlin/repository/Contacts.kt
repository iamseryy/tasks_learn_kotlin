package org.example.repository

import org.example.model.Person

interface Contacts {
//    fun add(person: Person): Boolean
//    fun update(person: Person)
    fun findPersonByName(name: String): Person?
    fun isPersonExists(name: String): Boolean
    fun addEmail(name: String, email: String)
    fun addPhone(name: String, phone: String)
    fun add(name: String, addContactAttr: (String) -> Person.EntryBuilder)
    fun findPeopleByEmail(email: String): HashSet<Person>
    fun findPeopleByPhone(phone: String): HashSet<Person>
}