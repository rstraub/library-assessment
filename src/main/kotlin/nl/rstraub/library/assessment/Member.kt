package nl.rstraub.library.assessment

class Member(val name: String) {
    val loanedBooks = mutableListOf<String>()

    operator fun plusAssign(book: String) {
        loanedBooks += book
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
