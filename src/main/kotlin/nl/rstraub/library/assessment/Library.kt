package nl.rstraub.library.assessment

class Library(vararg books: Book) {
    companion object {
        private const val MAXIMUM_AMOUNT_OF_LOANS = 7
    }

    private val inventory = books.toMutableList()
    private val members = mutableSetOf<Member>()

    fun inventory() = inventory.toList()

    fun members() = members.toSet()

    infix fun add(book: Book) {
        inventory += book
    }

    infix fun add(member: Member) {
        members += member
    }

    fun lend(book: Book, member: Member): Boolean {
        val loanIsAllowed = isLoanAllowed(book, member)

        if (loanIsAllowed) {
            remove(book)
            member loanBook book
        }

        return loanIsAllowed
    }

    fun returnBook(book: Book, member: Member): Boolean {
        val isBookLoaned = member.hasLoaned(book) && isLibraryMember(member)

        if (isBookLoaned) {
            add(book)
            member returnBook book
        }

        return isBookLoaned
    }

    private fun isLoanAllowed(book: Book, member: Member) = isBookAvailable(book) && isLoanAllowedFor(member)

    private fun isBookAvailable(book: Book) = book in inventory

    private fun isLoanAllowedFor(member: Member) = isLibraryMember(member) && !hasMaximumAmountOfLoans(member)

    private fun hasMaximumAmountOfLoans(member: Member) = member.amountOfLoanedBooks() == MAXIMUM_AMOUNT_OF_LOANS

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: Book) {
        inventory -= book
    }
}

fun main() {
    print(Library().inventory())
}
