package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val books = books.toMutableList()
    val inventory
        get() = books.toList()

    infix fun addBook(book: String) {
        books += book
    }
}

fun main() {
    print(Library().inventory)
}
