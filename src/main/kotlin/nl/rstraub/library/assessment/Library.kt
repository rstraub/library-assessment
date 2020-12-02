package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val currentInventory = books.toMutableList()
    val inventory
        get() = currentInventory.toList()

    private val currentMembers = mutableSetOf<Member>()
    val members
        get() = currentMembers.toSet()

    infix fun addBook(book: String) {
        currentInventory += book
    }

    infix fun addMember(member: String) {
        currentMembers += Member(member)
    }
}

fun main() {
    print(Library().inventory)
}
