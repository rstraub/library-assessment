package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val currentInventory = books.toMutableList()
    val inventory
        get() = currentInventory.toList()

    private val currentMembers = mutableListOf<String>()
    val members
        get() = currentMembers.toList()

    infix fun addBook(book: String) {
        currentInventory += book
    }

    infix fun addMember(member: String) {
        currentMembers.add(member)
    }
}

fun main() {
    print(Library().inventory)
}
