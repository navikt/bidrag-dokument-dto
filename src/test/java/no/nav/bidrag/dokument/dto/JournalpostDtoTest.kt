package no.nav.bidrag.dokument.dto

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class JournalpostDtoTest {

    @Test
    fun `skal hente hoveddokument `(){
        val jpUtenDok = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = emptyList()
        )
        val jp = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "Tittel hovedok"), DokumentDto(tittel = "Vedlegg1"), DokumentDto(tittel = "Vedlegg2"))
        )

        jpUtenDok.hentHoveddokument() shouldBe null
        jpUtenDok.hentTittel() shouldBe "Tittel på dokument"
        jp.hentHoveddokument() shouldNotBe null
        jp.hentHoveddokument()!!.tittel shouldBe "Tittel hovedok"
        jp.hentTittel() shouldBe "Tittel hovedok"
    }

    @Test
    fun `erFarskapUtelukket skal returnere false når hoveddokument tittel ikke inneholder farskap utelukket`(){
        val jp = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "dette er test"))
        )

        jp.erFarskapUtelukket() shouldBe false
    }

    @Test
    fun `erFarskapUtelukket skal returnere false når hoveddokument tittel ikke inneholder farskap utelukket i starten`(){
        val jp = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "dette er test FARSKAP UTELUKKET"))
        )

        jp.erFarskapUtelukket() shouldBe false
    }

    @Test
    fun `erFarskapUtelukket skal returnere true når journalpost tittel inneholder farskap utelukket`(){
        val jp = JournalpostDto(
            innhold = "FARSKAP UTELUKKET: Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "FARSKAP UTELUKKET: dette er test"))
        )

        jp.erFarskapUtelukket() shouldBe true
    }

    @Test
    fun `erFarskapUtelukket skal returnere true når hoveddokument tittel inneholder farskap utelukket med små bokstaver`(){
        val jp = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "Farskap utelukket : dette er test"))
        )

        jp.erFarskapUtelukket() shouldBe true
    }
    @Test
    fun `erFarskapUtelukket skal returnere true når hoveddokument tittel inneholder farskap utelukket`(){
        val jp = JournalpostDto(
            innhold = "Tittel på dokument",
            dokumenter = listOf(DokumentDto(tittel = "FARSKAP UTELUKKET: dette er test"))
        )

        jp.erFarskapUtelukket() shouldBe true
    }
}