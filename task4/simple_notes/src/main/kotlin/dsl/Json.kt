package org.example.dsl





class Json {
    private val children = hashMapOf<String, Any>()

    infix fun Json.p(property: Pair<String, Any>): Unit {
        this.children[property.first] = when (property.second) {
            is HashSet<*> -> (property.second as HashSet<*>).map { "\"${it}\"" }
            is String -> "\"${property.second}\""
            else -> property.second
        }
    }

    override fun toString(): String {
        return "{${children.entries.map { "\"${it.key}\": ${it.value}" }.joinToString(",")}}"
    }

}




fun json(callback: Json.() -> Unit): Json {
    val builder = Json()
    builder.callback()
    return builder
}


fun main() {
    val test = hashSetOf("1", "2", "3")

    val jsonContact = json {
        p("field1" to 1)
        p("field2" to hashSetOf("1", "2", "3"))
        p("field3" to json {
            p("field3.1" to "31")
        })
    }

    println(jsonContact.toString())
}

