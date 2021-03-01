package com.cloud.training.functions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles(profiles = ["test"])
internal class PropertiesKtTest {

    private val propertyKey = "hello.message"

    @Test
    fun `should return property for key from default bundle`() {
        val expected = "test"

        val actual = getProp(propertyKey)

        assertThat(actual).isEqualTo(expected)
    }

}