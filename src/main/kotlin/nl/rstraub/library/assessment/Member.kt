package nl.rstraub.library.assessment

class Member(val name: String) {
    private val loanedBooks = mutableListOf<Book>()

    fun loanedBooks() = loanedBooks.toList()

    fun amountOfLoanedBooks() = loanedBooks.size

    fun hasLoaned(book: Book) = book in loanedBooks

    infix fun add(book: Book) {
        loanedBooks += book
    }

    infix fun remove(book: Book) {
        loanedBooks -= book
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (name != other.name) return false

        return true
    }

    override fun hashCode() = name.hashCode()
}
