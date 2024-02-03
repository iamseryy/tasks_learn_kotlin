package org.example.model

data class Person(val name: String, val phoneNumber: HashSet<String>, val email: HashSet<String>) {
   class EntryBuilder {
        private var name: String = ""
        private var phoneNumber: HashSet<String> = hashSetOf()
        private var email: HashSet<String> = hashSetOf()

        fun name(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun phoneNumber(phoneNumber: String): EntryBuilder {
            if(!phoneNumber.isNullOrEmpty()) this.phoneNumber.add(phoneNumber)
            return this
        }

        fun email(email: String): EntryBuilder {
            if(!email.isNullOrEmpty()) this.email.add(email)
            return this
        }

        fun build() = Person(name = name, phoneNumber = phoneNumber, email = email)
    }
}
