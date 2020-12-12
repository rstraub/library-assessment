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

    infix fun add(member: Member) {
        currentMembers += member
    }

    infix fun lend(loanRequest: Pair<String, Member>): Boolean {
        require(loanRequest.first in currentInventory) {
           "Book '${loanRequest.first}' not in the library"
        }

        return true
    }
}

fun main() {
    print(Library().inventory)
}
