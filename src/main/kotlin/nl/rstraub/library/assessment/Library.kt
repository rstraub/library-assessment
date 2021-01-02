package nl.rstraub.library.assessment

class Library(vararg books: String) {
    companion object {
        private const val MAXIMUM_AMOUNT_OF_LOANS = 7
    }

    private val inventory = books.toMutableList()
    private val members = mutableSetOf<Member>()

    fun inventory() = inventory.toList()

    fun members() = members.toSet()

    infix fun add(book: String) {
        inventory += book
    }

    infix fun add(member: Member) {
        members += member
    }

    fun lend(book: String, member: Member): Boolean {
        val loanIsAllowed = isLoanAllowed(book, member)

        if (loanIsAllowed) {
            remove(book)
            member add book
        }

        return loanIsAllowed
    }

    fun returnBook(book: String, member: Member): Boolean {
        inventory += book
        member remove book
        return true
    }

    private fun isLoanAllowed(book: String, member: Member) = bookIsAvailable(book) && isLoanAllowedFor(member)

    private fun bookIsAvailable(book: String) = book in inventory

    private fun isLoanAllowedFor(member: Member) = isLibraryMember(member) && !hasMaximumAmountOfLoans(member)

    private fun hasMaximumAmountOfLoans(member: Member) = member.amountOfLoanedBooks() == 7

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: String) {
        inventory -= book
    }
}

fun main() {
    print(Library().inventory())
}
