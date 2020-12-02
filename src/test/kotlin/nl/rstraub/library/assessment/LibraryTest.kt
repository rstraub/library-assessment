package nl.rstraub.library.assessment

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainDuplicates
import io.kotest.matchers.collections.shouldHaveSize
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

            library addBook "the odyssey"
            val result = library.inventory

            result shouldHaveSize 2
            result shouldContainAll listOf("the iliad", "the odyssey")
        }

        "add duplicate books to the inventory" {
            val library = Library("the iliad")

            library addBook "the iliad"
            val result = library.inventory

            result shouldHaveSize 2
            result shouldContain "the iliad"
            result.shouldContainDuplicates()
        }
    }

    "addMember" should {
        "add a member to the library" {
            val library = Library()

            library addMember "homer"
            val result = library.members

            result shouldHaveSize 1
            result shouldContain "homer"
        }
    }
})
