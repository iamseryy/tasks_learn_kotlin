package org.example.dsl

class JsonList {
    private val children = mutableListOf<Any>()
    fun jobj(callback: JsonObject.() -> Unit) = children.add(JsonObject().apply(callback))
    override fun toString() = "[${children.joinToString(",")}]"
}