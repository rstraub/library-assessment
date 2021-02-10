package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.*
import io.kotest.matchers.shouldBe

internal class LibraryTest : WordSpec({
    lateinit var library: Library
    lateinit var member: Member
    lateinit var book: Book

    beforeEach {
        book = Book("1984", "1")
        member = Member("george")
        library = Library("Bibliotheek Utrecht Centrum", book)
    }

    "inventory" should {
        "return empty list given zero books" {
            Library("Test").inventory() shouldBe emptyList()
        }

        "return all books in the library" {
            val result = library.inventory()

            result shouldContain book
        }
    }

    "addBook" should {
        "add a book to the inventory" {
            val bookToAdd = Book("the odyssey", "2")

            library add bookToAdd
            val result = library.inventory()

            result shouldHaveSize 2
            result shouldContainAll listOf(book, bookToAdd)
        }

        "add duplicate books to the inventory" {
            val otherCopy = book.copy(serialCode = "2")
            library add otherCopy
            val result = library.inventory()

            result shouldHaveSize 2
            result shouldContain book
            result shouldContain otherCopy
        }
    }

    "addMember" should {
        "add a member to the library" {
            library add member
            val result = library.members()

            result shouldHaveSize 1
            result shouldContain member
        }

        "not add a duplicate member to the library" {
            library add member
            library add member
            val result = library.members()

            result shouldContain member
            result.shouldNotContainDuplicates()
        }
    }

    "lend" should {
        beforeEach {
            library add member
        }

        "return false if the book is not in the library" {
            library.lend(Book("404", "2"), member) shouldBe false

            member.loanedBooks().shouldBeEmpty()
        }

        "return false if the book is already loaned out" {
            library.lend(book, member)

            library.lend(book, member) shouldBe false
        }

        "return false if the member is not a member of the library" {
            library.lend(book, Member("gorge")) shouldBe false

            library.inventory() shouldContain book
        }

        "return false if the member already has seven loaned books" {
            member.addLoanedBook(Book("1", "8"))
            member.addLoanedBook(Book("2", "2"))
            member.addLoanedBook(Book("3", "3"))
            member.addLoanedBook(Book("4", "4"))
            member.addLoanedBook(Book("5", "5"))
            member.addLoanedBook(Book("6", "6"))
            member.addLoanedBook(Book("7", "7"))

            library.lend(book, member) shouldBe false

            member.loanedBooks() shouldNotContain book
        }

        "return true given the book passed from the library to the member" {
            library.lend(book, member) shouldBe true

            member.loanedBooks() shouldContain book
        }
    }

    "returnBook" should {
        beforeEach {
            library add member
        }

        "return true if the book was returned to the library and removed from the member" {
            library.lend(book, member)

            library.returnBook(book, member) shouldBe true

            member.loanedBooks() shouldNotContain book
        }

        "return false if the member does not have the book in his possession" {
            library.returnBook(book, member) shouldBe false
        }

        "return false if the member is not a member of the library" {
            val unknownMember = Member("john doe")
            unknownMember.addLoanedBook(book)

            library.returnBook(book, unknownMember) shouldBe false
        }

        "return false if the book is not property of the library" {
            val unknownBook = Book("Twilight", "1")
            member addLoanedBook unknownBook

            library.returnBook(unknownBook, member) shouldBe false
        }
    }

    "removeBook" should {
        beforeEach {
            library add member
        }

        "return true if the book is available and remove it from the inventory" {
            library remove book shouldBe true
            library.inventory() shouldNotContain book
        }

        "return false and leave the book if it is currently loaned out" {
            library.lend(book, member) shouldBe true

            library remove book shouldBe false
            library.inventory() shouldContain book
        }

        "return false if the book is not property of the library" {
            val unknownBook = Book("Twilight", "1")

            library remove unknownBook shouldBe false
        }
    }

    "currentOwnerOf" should {
        beforeEach {
            library add member
        }

        "return the library if the book is in the library inventory" {
            library currentOwnerOf book shouldBe library
        }

        "return the member currently loaning the book" {
            library.lend(book, member)

            library currentOwnerOf book shouldBe member
        }
    }
})
