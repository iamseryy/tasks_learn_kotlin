package org.example.repository

import org.example.model.Person

interface Contacts {
    fun add(person: Person): Boolean
    fun findLast(): Person
}