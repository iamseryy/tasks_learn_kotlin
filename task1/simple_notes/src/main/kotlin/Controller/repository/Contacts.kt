package org.example.Controller.repository

import org.example.model.Entry

interface Contacts {
    fun add(entry: Entry): Boolean
}