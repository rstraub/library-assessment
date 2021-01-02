package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class MemberTest : WordSpec({
    lateinit var member: Member
    lateinit var book: Book

    beforeEach {
        member = Member("tester")
        book = Book("1984")
    }

    "loanBook" should {
        "add the book to the inventory of the member and set loaned to true on the book" {
            member loanBook book

            member hasLoaned book shouldBe true
            book.isLoanedOut shouldBe true
        }
    }

    "returnBook" should {
        "remove the book from the member's inventory and set loaned to false on the book" {
            member loanBook book
            member returnBook book

            member hasLoaned book shouldBe false
            book.isLoanedOut shouldBe false
        }
    }
})
