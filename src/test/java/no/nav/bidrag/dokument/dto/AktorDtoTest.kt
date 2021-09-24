package no.nav.bidrag.dokument.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

@Suppress("NonAsciiCharacters")
internal class AktorDtoTest {
    @ParameterizedTest
    @EnumSource(IdentType::class)
    fun `skal hente IdentType fra AktorDto`(type: IdentType) {
        val aktor = AktorDto(type = type.name)

        assertThat(aktor.hentIdentType()).`as`(type.name).isEqualTo(type)
    }

    @Test
    fun `skal hente ident type som null når typen i aktor er ukjent`() {
        val aktor = AktorDto(type = "jon blund")

        assertThat(aktor.hentIdentType()).isNull()
    }
}
