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
})
