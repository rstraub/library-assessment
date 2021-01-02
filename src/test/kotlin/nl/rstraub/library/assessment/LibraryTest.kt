package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainDuplicates
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.collections.shouldNotContainDuplicates
import io.kotest.matchers.shouldBe

internal class LibraryTest : WordSpec({
    "inventory" should {
        "return empty list given zero books" {
            Library().inventory() shouldBe emptyList()
        }

        "return all books in the library" {
            val library = Library("moby dick", "picture of dorian gray")

            val result = library.inventory()

            result shouldContain "moby dick"
            result shouldContain "picture of dorian gray"
        }
    }

    "addBook" should {
        "add a book to the inventory" {
            val library = Library("the iliad")

            library add "the odyssey"
            val result = library.inventory()

            result shouldHaveSize 2
            result shouldContainAll listOf("the iliad", "the odyssey")
        }

        "add duplicate books to the inventory" {
            val library = Library("the iliad")

            library add "the iliad"
            val result = library.inventory()

            result shouldHaveSize 2
            result shouldContain "the iliad"
            result.shouldContainDuplicates()
        }
    }

    "addMember" should {
        "add a member to the library" {
            val library = Library()
            val member = Member("homer")

            library add member
            val result = library.members

            result shouldHaveSize 1
            result shouldContain member
        }

        "not add a duplicate member to the library" {
            val library = Library()
            val member = Member("oscar wilde")

            library add member
            library add member
            val result = library.members

            result shouldContain member
            result.shouldNotContainDuplicates()
        }
    }

    "lend" should {
        lateinit var member: Member
        lateinit var book: String
        lateinit var library: Library

        beforeEach {
            book = "1984"
            library = Library(book)
            member = Member("george")
            library add member
        }

        "return false if the book is not in the library" {
            library.lend("404", member) shouldBe false

            member.loanedBooks().shouldBeEmpty()
        }

        "return false if the member is not a member of the library" {
            library.lend(book, Member("gorge")) shouldBe false

            library.inventory() shouldContain book
        }

        "return false if the member already has seven loaned books" {
            member.add("1")
            member.add("2")
            member.add("3")
            member.add("4")
            member.add("5")
            member.add("6")
            member.add("7")

            library.lend(book, member) shouldBe false

            library.inventory() shouldContain book
            member.loanedBooks() shouldNotContain book
        }

        "return true given the book passed from the library to the member" {
            library.lend(book, member) shouldBe true

            member.loanedBooks() shouldContain book
            library.inventory() shouldNotContain book
        }
    }

    "returnBook" should {
        lateinit var member: Member
        lateinit var book: String
        lateinit var library: Library

        beforeEach {
            book = "1984"
            library = Library()
            member = Member("george")
            member add book
            library add member
        }

        "return true if the book was returned to the library and removed from the member" {
            library.returnBook(book, member) shouldBe true

            library.inventory() shouldContain book
            member.loanedBooks() shouldNotContain book
        }

        "return false if the member does not have the book in his possession" {}

        "return false if the member is not a member of the library" {}
    }
})
