package nl.rstraub.library.assessment

class Library(vararg books: String) {
    private val inventory = books.toMutableList()
    private val members = mutableSetOf<Member>()

    fun inventory() = inventory.toList()

    fun members() = members.toSet()

    infix fun add(book: String) {
        inventory += book
    }

    infix fun add(member: Member) {
        members += member
    }

    fun lend(book: String, member: Member): Boolean {
        val loanIsAllowed = loanIsAllowed(book, member)

        if (loanIsAllowed) {
            remove(book)
            member add book
        }

        return loanIsAllowed
    }

    fun returnBook(book: String, member: Member): Boolean {
        inventory += book
        member remove book
        return true
    }

    private fun loanIsAllowed(book: String, member: Member) =
        bookIsAvailable(book) && isLibraryMember(member) && member.loanIsAllowed()

    private fun bookIsAvailable(book: String) = book in inventory

    private fun isLibraryMember(member: Member) = member in members

    private infix fun remove(book: String) {
        inventory -= book
    }
}

fun main() {
    print(Library().inventory())
}
