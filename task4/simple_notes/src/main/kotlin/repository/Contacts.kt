package org.example.repository

import org.example.model.Person

interface Contacts {
    companion object {
        const val NOT_INITIALIZED = "Not initialized"
    }
    fun findPersonByName(name: String): Person?
    fun addEmail(name: String, email: String)
    fun addPhone(name: String, phone: String)
    fun add(name: String, contactAttribute: String, addContact: (String, String) -> Unit)
    fun findPeopleByEmail(email: String): HashSet<Person>
    fun findPeopleByPhone(phone: String): HashSet<Person>
    fun findAll(): HashSet<Person>
    fun isEmpty(): Boolean
}