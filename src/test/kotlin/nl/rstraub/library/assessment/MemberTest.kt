package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldBeEmpty

internal class MemberTest : WordSpec({
    "loanedBooks" should {
        "return an empty list given no loaned books" {
            val member = Member("homer")

            member.loanedBooks.shouldBeEmpty()
        }
    }

    "loanBook" should {
        lateinit var member: Member
        lateinit var library: Library
        beforeEach {
            library = Library("1984")
            member = Member("george")
        }

        "should throw an exception if the book is not in the library" {
        }

        "should throw an exception if the member is not a member of the library" {

        }

        "should add the book to the loanedBooks" {

        }
    }
})
