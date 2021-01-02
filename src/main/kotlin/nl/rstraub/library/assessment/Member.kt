package nl.rstraub.library.assessment

class Member(val name: String) {
    private val loanedBooks = mutableListOf<String>()

    fun loanedBooks() = loanedBooks.toList()

    fun amountOfLoanedBooks() = loanedBooks.size

    infix fun add(book: String) {
        loanedBooks += book
    }

    infix fun remove(book: String) {
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
