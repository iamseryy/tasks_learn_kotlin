package org.example.model

data class Entry(val name: String, val phoneNumber: String, val email: String) {
   class EntryBuilder {
        private var name: String = ""
        private var phoneNumber: String = ""
        private var email: String = ""

        fun name(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun phoneNumber(phoneNumber: String): EntryBuilder {
            this.phoneNumber = phoneNumber
            return this
        }

        fun email(email: String): EntryBuilder {
            this.email = email
            return this
        }

        fun build() = Entry(name = name, phoneNumber = phoneNumber, email = email)
    }
}
