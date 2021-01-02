package nl.rstraub.library.assessment

class Library(vararg books: Book) {
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
        return if (!isLoanAllowed(book, member)) false
        else member loanBook book
    }

    fun returnBook(book: Book, member: Member): Boolean {
        val isBookLoaned = member.hasLoaned(book) && isLibraryMember(member)
        if (book !in inventory) return false

        if (isBookLoaned) {
            member returnBook book
        }

        return isBookLoaned
    }

    private fun isLoanAllowed(book: Book, member: Member) = isBookAvailable(book) && isLibraryMember(member)

    private fun isBookAvailable(book: Book) = book in inventory && !book.isLoanedOut

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: Book) {
        inventory -= book
    }
}

fun main() {
    print(Library().inventory())
}
