package nl.rstraub.library.assessment

class Library(vararg books: String) {
    val inventory = books.toList()
}

fun main() {
    print(Library().inventory)
}
