package nl.rstraub.library.assessment

class Library(vararg books: Book) {
    private val inventory = books.toMutableSet()
    private val members = mutableSetOf<Member>()
    private val loans = mutableMapOf<String, Loan>()

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
        else {
            loans[book.serialCode] = Loan(book, member)
            member loanBook book
        }
    }

    private fun isLoanAllowed(book: Book, member: Member) =
        isLibraryBook(book) && isLibraryMember(member) && !isBookLoaned(book)

    private fun isBookLoaned(book: Book) = book.serialCode in loans

    fun returnBook(book: Book, member: Member): Boolean {
        return if (!isReturnAllowed(book, member)) false
        else {
            loans.remove(book.serialCode)
            member returnBook book
        }
    }

    private fun isReturnAllowed(book: Book, member: Member) =
        isLibraryMember(member) && isLibraryBook(book) && isBookLoaned(book)

    private fun isLibraryMember(member: Member) = member in members
    private fun isLibraryBook(book: Book) = book in inventory

    infix fun remove(book: Book): Boolean {
        if (!isLibraryBook(book) || book.isLoanedOut) return false

        inventory -= book
        return true
    }
}

fun main() {
    print(Library().inventory())
}
