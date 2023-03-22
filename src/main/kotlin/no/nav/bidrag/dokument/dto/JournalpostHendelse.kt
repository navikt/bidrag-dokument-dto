package no.nav.bidrag.dokument.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate

enum class HendelseType {
    JOURNALFORING,
    ENDRING
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class JournalpostHendelse(
    val journalpostId: String = "-1",
    val aktorId: String? = null,
    val fnr: String? = null,
    val behandlingstema: String? = null,
    val tittel: String? = null,
    @Deprecated("Bruk tema istedenfor")
    val fagomrade: String? = null,
    val tema: String? = null,
    val batchId: String? = null,
    val journalposttype: String? = null,
    val hendelseType: HendelseType? = null,
    val enhet: String? = null,
    @Deprecated("Bruk status istedenfor")
    val journalstatus: String? = null,
    val status: String? = null,
    val sporing: Sporingsdata? = null,
    val sakstilknytninger: List<String>? = emptyList(),
    val dokumentDato: LocalDate? = null,
    val journalfortDato: LocalDate? = null,
) {
    fun erHendelseTypeJournalforing() = hendelseType == HendelseType.JOURNALFORING
    fun harEnhet() = enhet != null
    fun harAktorId() = aktorId != null
    fun hentStatus(): JournalpostStatus? = konverterStatus(journalstatus) ?: konverterStatus(status)

    private fun konverterStatus(status: String?): JournalpostStatus? {
        return when (status) {
            JournalpostStatus.MOTTATT.name, Journalstatus.MOTTATT -> JournalpostStatus.MOTTATT
            JournalpostStatus.FEILREGISTRERT.name, Journalstatus.FEILREGISTRERT -> JournalpostStatus.FEILREGISTRERT
            JournalpostStatus.UNDER_PRODUKSJON.name, Journalstatus.UNDER_PRODUKSJON -> JournalpostStatus.UNDER_PRODUKSJON
            JournalpostStatus.FERDIGSTILT.name, Journalstatus.FERDIGSTILT -> JournalpostStatus.FERDIGSTILT
            JournalpostStatus.EKSPEDERT.name, Journalstatus.EKSPEDERT -> JournalpostStatus.EKSPEDERT
            JournalpostStatus.AVBRUTT.name, Journalstatus.AVBRUTT -> JournalpostStatus.AVBRUTT
            JournalpostStatus.KLAR_FOR_DISTRIBUSJON.name, Journalstatus.KLAR_TIL_PRINT -> JournalpostStatus.KLAR_FOR_DISTRIBUSJON
            JournalpostStatus.UTGÅR.name, Journalstatus.UTGAR -> JournalpostStatus.UTGÅR
            JournalpostStatus.RETUR.name, Journalstatus.RETUR -> JournalpostStatus.RETUR
            JournalpostStatus.RESERVERT.name, Journalstatus.RESERVERT -> JournalpostStatus.RESERVERT
            JournalpostStatus.JOURNALFØRT.name, Journalstatus.JOURNALFORT -> JournalpostStatus.JOURNALFØRT
            else -> null
        }
    }

    fun hentJournalposttype(): JournalpostType? {
        return when (journalposttype) {
            JournalpostType.UTGÅENDE.name, DokumentType.UTGÅENDE -> JournalpostType.UTGÅENDE
            JournalpostType.NOTAT.name, DokumentType.NOTAT -> JournalpostType.NOTAT
            JournalpostType.INNGÅENDE.name, DokumentType.INNGÅENDE -> JournalpostType.INNGÅENDE
            else -> null
        }
    }

    fun harJournalpostIdPrefix() = journalpostId.contains("-")
    fun harJournalpostId() = journalpostId != "-1"
    fun hentJournalpostIdNumerisk(): Long = JournalpostId(journalpostId).idNumerisk!!
    fun erBidragJournalpost() = JournalpostId(journalpostId).erSystemBidrag
    fun erJoarkJournalpost() = JournalpostId(journalpostId).erSystemJoark
    fun erForsendelse() = JournalpostId(journalpostId).erSystemForsendelse
    fun erInngående() = hentJournalposttype() == JournalpostType.INNGÅENDE
    fun erUtgående() = hentJournalposttype() == JournalpostType.UTGÅENDE

    fun hentEndretAvEnhetsnummer() = sporing?.enhetsnummer ?: enhet
    fun hentSaksbehandlerInfo() = sporing?.lagSaksbehandlerInfo() ?: "ukjent saksbehandler"

    fun printSummary() =
        "{journalpostId=$journalpostId,tema=${tema},enhet=$enhet,saksbehandlerEnhet=${sporing?.enhetsnummer},journalstatus=${hentStatus()},saker=${sakstilknytninger},dokumentDato=${dokumentDato},journalfortDato=${journalfortDato},tittel=${tittel},behandlingstema=$behandlingstema....}"
}

data class Sporingsdata(
    var correlationId: String? = null,
    var brukerident: String? = null,
    var saksbehandlersNavn: String? = "ukjent saksbehandler",
    var enhetsnummer: String? = null
) {

    fun lagSaksbehandlerInfo(saksbehandlerEnhet: String? = null) =
        if (brukerident == null && saksbehandlersNavn == null) "ukjent saksbehandler" else hentBrukeridentMedSaksbehandler(
            saksbehandlerEnhet ?: enhetsnummer ?: ""
        )

    private fun hentBrukeridentMedSaksbehandler(enhetsnummer: String) =
        "${saksbehandlersNavn ?: "Ukjent"} (${brukerident ?: "Ukjent"}, $enhetsnummer)"
}

enum class JournalpostStatus {
    MOTTATT,
    JOURNALFØRT,
    EKSPEDERT,
    DISTRIBUERT,
    AVBRUTT,
    KLAR_FOR_DISTRIBUSJON,
    RETUR,
    FERDIGSTILT,
    FEILREGISTRERT,
    RESERVERT,
    UTGÅR,
    UNDER_PRODUKSJON,
    UKJENT,
}