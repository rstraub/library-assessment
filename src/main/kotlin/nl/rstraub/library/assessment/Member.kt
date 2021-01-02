package nl.rstraub.library.assessment

class Member(val name: String) {
    private companion object {
        const val MAXIMUM_AMOUNT_OF_LOANED_BOOKS = 7
    }

    private val loanedBooks = mutableListOf<Book>()

    fun loanedBooks() = loanedBooks.toList()

    infix fun hasLoaned(book: Book) = book in loanedBooks

    infix fun loanBook(book: Book) {
        loanedBooks += book
        book.isLoanedOut = true
    }

    infix fun returnBook(book: Book) {
        loanedBooks -= book
        book.isLoanedOut = false
    }

    fun isAllowedToLoan() = loanedBooks.size < MAXIMUM_AMOUNT_OF_LOANED_BOOKS

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (name != other.name) return false

        return true
    }

    override fun hashCode() = name.hashCode()
}
