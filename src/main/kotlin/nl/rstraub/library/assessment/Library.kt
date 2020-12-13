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

    infix fun lend(loanRequest: Pair<String, Member>) =
        loanRequest.first in inventory && loanRequest.second in members
}

fun main() {
    print(Library().inventory)
}
