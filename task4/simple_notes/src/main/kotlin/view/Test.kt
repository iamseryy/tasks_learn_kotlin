package org.example.view

class Test {
}


data class Item(
    val text: String,
    val url: String?
)

fun main() {

    val items = listOf(
        Item("Item 1", "http://example.com"),
        Item("Item 2", "http://example2.com")
    )

    val htmlPage = html {
        body {

            ul {
                for (item in items) {
                    li {
                        if (item.url != null) {
                            a(href = item.url) {
                                text(item.text)
                            }
                        } else {
                            text(item.text)
                        }
                    }
                }
            }

            b {
                a(href = "http://google.com") {
                    text("Google it")
                }
            }
        }
    }

    println(htmlPage)
}

class UrlLink(
    val url: String,
    var text: String
)


class Link(private val url: String) : Tag() {

    override fun toString(): String {
        return "<a href=\"$url\">${children.joinToString("")}</a>"
    }
}

open class Tag {

    protected val children = mutableListOf<Any>()

    fun b(callback: Tag.() -> Unit) {
        children.add(Bold().apply {
            callback()
        })
    }

    fun a(href: String, callback: Tag.() -> Unit) {
        children.add(Link(href).apply {
            callback()
        })
    }

    fun text(text: String) {
        children.add(text)
    }
}

class Bold : Tag() {

    override fun toString(): String {
        return "<b>${children.joinToString("")}</b>"
    }
}

class Body : Tag() {

    fun ul(callback: List.() -> Unit) {
        children.add(List().apply(callback))
    }

    override fun toString(): String {
        return "<body>${children.joinToString("")}</body>"
    }
}

class ListItem : Tag() {

    override fun toString(): String {
        return "<li>${children.joinToString("")}</li>"
    }
}

class List {

    private val children = mutableListOf<Any>()

    fun li(callback: ListItem.() -> Unit) {
        children.add(ListItem().apply(callback))
    }

    override fun toString(): String {
        return "<ul>${children.joinToString("")}</ul>"
    }
}

class Html {
    private val _body = Body()

    fun body(callback: Body.() -> Unit) {
        _body.callback()
    }

    override fun toString(): String {
        return "$_body"
    }
}

fun html(callback: Html.() -> Unit): Html {
    val builder = Html()
    builder.callback()
    return builder
}

