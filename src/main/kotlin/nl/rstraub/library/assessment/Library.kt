package nl.rstraub.library.assessment

class Library(override val name: String, vararg books: Book) : BookOwner {
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
            member addLoanedBook book
        }
    }

    private fun isLoanAllowed(book: Book, member: Member) =
        isLibraryBook(book) && isLibraryMember(member) && !isBookLoaned(book)

    private fun isBookLoaned(book: Book) = book.serialCode in loans

    fun returnBook(book: Book, member: Member): Boolean {
        return if (!isReturnAllowed(book, member)) false
        else {
            loans.remove(book.serialCode)
            member returnLoanedBook book
        }
    }

    private fun isReturnAllowed(book: Book, member: Member) =
        isLibraryMember(member) && isLibraryBook(book) && isBookLoaned(book)

    private fun isLibraryMember(member: Member) = member in members
    private fun isLibraryBook(book: Book) = book in inventory

    infix fun remove(book: Book): Boolean {
        if (!isLibraryBook(book) || isBookLoaned(book)) return false

        inventory -= book
        return true
    }

    infix fun currentOwnerOf(book: Book) =
        when (val loan = loans[book.serialCode]) {
            null -> this
            else -> loan.member
        }

    override fun toString() = name
}

fun main() {
    print(Library("Utrecht Centrum").inventory())
}
