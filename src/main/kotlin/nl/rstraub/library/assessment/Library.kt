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

    infix fun lend(loanRequest: Pair<String, Member>): Boolean {
        val request = LoanRequest(loanRequest.first, loanRequest.second)
        return loanIsAllowed(request)
    }

    private fun loanIsAllowed(loanRequest: LoanRequest) =
        bookIsAvailable(loanRequest.book) && isLibraryMember(loanRequest.member)

    private fun bookIsAvailable(book: String) = book in inventory

    private fun isLibraryMember(member: Member) = member in members
}

fun main() {
    print(Library().inventory)
}
