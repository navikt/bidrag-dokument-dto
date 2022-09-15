package no.nav.bidrag.dokument.dto

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class JournalpostHendelseTest {

    @Test
    fun `skal hente saksbehandlerInfo når brukerident er null`() {
        val hendelse = JournalpostHendelse(sporing = Sporingsdata("", null, "Navn Navnesen", "4806"))
        Assertions.assertThat(hendelse.hentSaksbehandlerInfo()).isEqualTo("Navn Navnesen (ukjent, 4806)")
    }

    @Test
    fun `skal hente saksbehandlerInfo når saksbehandlernavn er null`() {
        val hendelse = JournalpostHendelse(sporing = Sporingsdata("", "Z9999", null, "4806"))
        Assertions.assertThat(hendelse.hentSaksbehandlerInfo()).isEqualTo("ukjent navn (Z9999, 4806)")
    }

    @Test
    fun `skal hente saksbehandlerInfo når ident og saksbehandlernavn er null`() {
        val hendelse = JournalpostHendelse(sporing = Sporingsdata("", null, null, "4806"))
        Assertions.assertThat(hendelse.hentSaksbehandlerInfo()).isEqualTo("ukjent saksbehandler")
    }

    @Test
    fun `skal hente saksbehandlerInfo når ident og saksbehandlernavn er kjent`() {
        val hendelse = JournalpostHendelse(sporing = Sporingsdata("", "Z99999", "Navn Navnesen", "4806"))
        Assertions.assertThat(hendelse.hentSaksbehandlerInfo()).isEqualTo("Navn Navnesen (Z99999, 4806)")
    }
}