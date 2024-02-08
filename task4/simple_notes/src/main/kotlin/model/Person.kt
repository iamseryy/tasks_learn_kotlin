package org.example.model

data class Person(val name: String, val phones: HashSet<String>, val emails: HashSet<String>) {
   class EntryBuilder {
        private var name: String = ""
        private var phones: HashSet<String> = hashSetOf()
        private var emails: HashSet<String> = hashSetOf()

        fun name(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun phone(phone: String): EntryBuilder {
            if(phone.isNotEmpty()) this.phones.add(phone)
            return this
        }

        fun email(email: String): EntryBuilder {
            if(email.isNotEmpty()) this.emails.add(email)
            return this
        }

        fun build() = Person(name = name, phones = phones, emails = emails)
    }
}
