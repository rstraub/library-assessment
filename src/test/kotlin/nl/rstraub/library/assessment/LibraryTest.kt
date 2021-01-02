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
            Library().inventory shouldBe emptyList()
        }

        "return all books in the library" {
            val library = Library("moby dick", "picture of dorian gray")

            val result = library.inventory

            result shouldContain "moby dick"
            result shouldContain "picture of dorian gray"
        }
    }

    "addBook" should {
        "add a book to the inventory" {
            val library = Library("the iliad")

            library add "the odyssey"
            val result = library.inventory

            result shouldHaveSize 2
            result shouldContainAll listOf("the iliad", "the odyssey")
        }

        "add duplicate books to the inventory" {
            val library = Library("the iliad")

            library add "the iliad"
            val result = library.inventory

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

        "should return false if the book is not in the library" {
            library.lend("404", member) shouldBe false
        }

        "should return false if the member is not a member of the library" {
            val result = library.lend(book, Member("gorge"))

            result shouldBe false
            library.inventory shouldContain book
        }

        "should return true given valid loan request" {
            val result = library.lend(book, member)

            result shouldBe true
        }

        "should remove the book from the library given valid loan request" {
            library.inventory shouldContain book
            library.lend(book, member)
            library.inventory shouldNotContain book
        }

        "should add the book to the member given a valid loan request" {
            member.loanedBooks.shouldBeEmpty()
            library.lend(book, member)
            member.loanedBooks shouldContain book
        }
    }
})
