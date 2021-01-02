package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val currentInventory = books.toMutableList()
    val inventory
        get() = currentInventory.toList()

    private val currentMembers = mutableSetOf<Member>()
    val members
        get() = currentMembers.toSet()

    operator fun plusAssign(book: String) {
        currentInventory += book
    }

    operator fun plusAssign(member: Member) {
        currentMembers += member
    }

    infix fun lend(loanRequest: LoanRequest) =
        loanIsAllowed(loanRequest)
            .also { remove(loanRequest.book) }
            .also { loanRequest.member += loanRequest.book }

    private fun loanIsAllowed(loanRequest: LoanRequest) =
        bookIsAvailable(loanRequest.book) && isLibraryMember(loanRequest.member)

    private fun bookIsAvailable(book: String) = book in inventory

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: String) {
        currentInventory -= book
    }
}

fun main() {
    print(Library().inventory)
}
