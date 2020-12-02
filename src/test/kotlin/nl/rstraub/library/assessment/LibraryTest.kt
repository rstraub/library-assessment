package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldContain
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
})
