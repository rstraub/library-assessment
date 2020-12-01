package nl.rstraub.library.assessment

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LibraryTest : StringSpec ({
"inventory should return empty list given zero books" {
        Library().inventory shouldBe emptyList()
    }
})
