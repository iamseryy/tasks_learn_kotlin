package org.example.model

data class Person(val name: String, val phoneNumber: HashSet<String>, val email: HashSet<String>) {
   class EntryBuilder {
        private lateinit var name: String
        private lateinit var phoneNumber: HashSet<String>
        private lateinit var email: HashSet<String>

        fun name(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun phoneNumber(phoneNumber: String): EntryBuilder {
            this.phoneNumber.add(phoneNumber)
            return this
        }

        fun email(email: String): EntryBuilder {
            this.email.add(email)
            return this
        }

        fun build() = Person(name = name, phoneNumber = phoneNumber, email = email)
    }
}
