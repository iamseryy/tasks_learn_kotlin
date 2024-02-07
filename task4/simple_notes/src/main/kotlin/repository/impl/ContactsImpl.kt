package org.example.repository.impl

import org.example.repository.Contacts
import org.example.model.Person

class ContactsImpl: Contacts {

    private val people = HashMap<String, Person>()

    override fun findPersonByName(name: String) = people[name]

//    override fun add(person: Person) = people.add(person)
//
//    override fun update(person: Person) {
//        findPersonByName(person.name)?.let{
//            it.email.addAll(person.email)
//            it.phoneNumber.addAll(person.phoneNumber)
//        }
//    }

    override fun addEmail(name: String, email: String) {
        TODO("Not yet implemented")
    }

    override fun addPhone(name: String, phone: String) {
        TODO("Not yet implemented")
    }

    override fun add(name: String, addContactAttr: (String) -> Person.EntryBuilder) {
        addContactAttr(name).
    }

    override fun isPersonExists(name: String)= people.containsKey(name)

    override fun findPeopleByEmail(email: String) = people.filter { it.value.emails.contains(email) }.map { it.value }.toHashSet()

    override fun findPeopleByPhone(phone: String) = people.filter { it.value.phones.contains(phone) }.map { it.value }.toHashSet()
}