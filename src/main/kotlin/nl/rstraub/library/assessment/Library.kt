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

    private fun isLoanAllowed(book: Book, member: Member) = isBookAvailable(book) && isLibraryMember(member)
    private fun isBookAvailable(book: Book) = isLibraryBook(book) && !book.isLoanedOut

    fun returnBook(book: Book, member: Member): Boolean {
        return if (!isReturnAllowed(book, member)) false
        else member returnBook book
    }

    private fun isReturnAllowed(book: Book, member: Member) =
        isLibraryMember(member) && isLibraryBook(book) && book.isLoanedOut

    private fun isLibraryMember(member: Member) = member in members
    private fun isLibraryBook(book: Book) = book in inventory

    infix fun remove(book: Book): Boolean {
        inventory -= book
        return true
    }
}

fun main() {
    print(Library().inventory())
}
