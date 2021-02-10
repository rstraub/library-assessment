package nl.rstraub.library.assessment

class Member(override val name: String) : BookOwner {
    private companion object {
        const val MAXIMUM_AMOUNT_OF_LOANED_BOOKS = 7
    }

    private val loanedBooks = mutableListOf<Book>()

    fun loanedBooks() = loanedBooks.toList()

    infix fun loanBook(book: Book): Boolean {
        if (!isAllowedToLoan()) return false

        loanedBooks += book

        return true
    }

    private fun isAllowedToLoan() = loanedBooks.size < MAXIMUM_AMOUNT_OF_LOANED_BOOKS

    infix fun returnBook(book: Book): Boolean {
        if (!hasLoaned(book)) return false

        loanedBooks -= book

        return true
    }

    private fun hasLoaned(book: Book) = book in loanedBooks

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (name != other.name) return false

        return true
    }

    override fun hashCode() = name.hashCode()
    override fun toString() = name
}
