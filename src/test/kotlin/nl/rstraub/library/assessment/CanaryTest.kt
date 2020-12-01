package nl.rstraub.library.assessment

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CanaryTest : StringSpec ({
    "canary" {
        true shouldBe false
    }
})
