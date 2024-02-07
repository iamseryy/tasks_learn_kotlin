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

        fun phoneNumber(phone: String): EntryBuilder {
            if(!phone.isNullOrEmpty()) this.phones.add(phone)
            return this
        }

        fun email(email: String): EntryBuilder {
            if(!email.isNullOrEmpty()) this.emails.add(email)
            return this
        }

        fun build() = Person(name = name, phones = phones, emails = emails)
    }
}
