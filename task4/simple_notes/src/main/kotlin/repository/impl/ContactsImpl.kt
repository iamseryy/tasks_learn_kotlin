package org.example.repository.impl

import org.example.repository.Contacts
import org.example.model.Person

class ContactsImpl: Contacts {

    private val people = HashMap<String, Person>()

    override fun findPersonByName(name: String) = people[name]

    override fun addEmail(name: String, email: String) {
        if (people.containsKey(name)) {
            people[name]?.emails?.add(email)
        } else {
            people[name] = Person.EntryBuilder()
                .name(name)
                .email(email)
                .build()
        }

    }

    override fun addPhone(name: String, phone: String) {
        if (people.containsKey(name)) {
            people[name]?.phones?.add(phone)
        } else {
            people[name] = Person.EntryBuilder()
                .name(name)
                .phone(phone)
                .build()
        }
    }

    override fun add(name: String, contactAttribute: String, addContact: (String, String) -> Unit) {
        addContact(name, contactAttribute)
    }

    override fun findPeopleByEmail(email: String) = people.filter { it.value.emails.contains(email) }.map { it.value }.toHashSet()

    override fun findPeopleByPhone(phone: String) = people.filter { it.value.phones.contains(phone) }.map { it.value }.toHashSet()
}