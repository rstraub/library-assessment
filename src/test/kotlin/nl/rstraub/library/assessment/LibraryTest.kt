package nl.rstraub.library.assessment

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LibraryTest : StringSpec ({
    "canary" {
        true shouldBe false
    }
})
