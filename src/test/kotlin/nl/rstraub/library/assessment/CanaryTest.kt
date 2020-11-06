package nl.rstraub.library.assessment

import assertk.assertThat
import assertk.assertions.isFalse
import org.junit.jupiter.api.Test

internal class CanaryTest {
    @Test
    internal fun `first failing test`() {
        assertThat(true).isFalse()
    }
}
