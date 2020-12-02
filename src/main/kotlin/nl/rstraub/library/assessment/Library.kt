package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val currentInventory = books.toMutableList()
    val inventory
        get() = currentInventory.toList()

    private val currentMembers = mutableSetOf<String>()
    val members
        get() = currentMembers.toSet()

    infix fun addBook(book: String) {
        currentInventory += book
    }

    infix fun addMember(member: String) {
        currentMembers += member
    }
}

fun main() {
    print(Library().inventory)
}
