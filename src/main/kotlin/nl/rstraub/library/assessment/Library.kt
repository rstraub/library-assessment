package nl.rstraub.library.assessment

class Library(vararg books: String) {
    val inventory = books.toMutableList()

    infix fun addBook(book: String) {
        inventory += book
    }
}

fun main() {
    print(Library().inventory)
}
