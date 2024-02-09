package org.example.dsl

class Json {
    private val _jlist = JsonList()
    fun jlist(callback: JsonList.() -> Unit) = _jlist.callback()
    override fun toString() = "$_jlist"
}

fun json(callback: Json.() -> Unit): Json {
    val builder = Json()
    builder.callback()
    return builder
}