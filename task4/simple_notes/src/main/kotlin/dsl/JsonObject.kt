package org.example.dsl

class JsonObject {
    private val children = hashMapOf<String, Any>()

    infix fun JsonObject.p(property: Pair<String, Any>) {
        this.children[property.first] = when (property.second) {
            is HashSet<*> -> "[${(property.second as HashSet<*>).joinToString(",") { "\"${it}\"" }}]"
            is String -> "\"${property.second}\""
            else -> property.second
        }
    }

    override fun toString()= "{${children.entries.joinToString(",") { "\"${it.key}\": ${it.value}" }}}"
}