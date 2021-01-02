package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val currentInventory = books.toMutableList()
    val inventory
        get() = currentInventory.toList()

    private val currentMembers = mutableSetOf<Member>()
    val members
        get() = currentMembers.toSet()

    infix fun add(book: String) {
        currentInventory += book
    }

    infix fun add(member: Member) {
        currentMembers += member
    }

    fun lend(book: String, member: Member): Boolean {
        val loanIsAllowed = loanIsAllowed(book, member)

        if (loanIsAllowed) {
            remove(book)
            member add book
        }

        return loanIsAllowed
    }

    private fun loanIsAllowed(book: String, member: Member) =
        bookIsAvailable(book) && isLibraryMember(member)

    private fun bookIsAvailable(book: String) = book in inventory

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: String) {
        currentInventory -= book
    }
}

fun main() {
    print(Library().inventory)
}
