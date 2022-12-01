package no.nav.bidrag.dokument.dto

import java.time.LocalDate

enum class HendelseType {
    JOURNALFORING,
    ENDRING
}
data class JournalpostHendelse(
    val journalpostId: String = "na",
    val aktorId: String? = null,
    val fnr: String? = null,
    val behandlingstema: String? = null,
    val tittel: String? = null,
    val fagomrade: String? = null,
    val journalposttype: String? = null,
    val hendelseType: HendelseType? = null,
    val enhet: String? = null,
    val journalstatus: String? = null,
    val sporing: Sporingsdata? = null,
    var sakstilknytninger: List<String>? = emptyList(),
    val dokumentDato: LocalDate? = null,
    val journalfortDato: LocalDate? = null,
) {

    fun erHendelseTypeJournalforing() = hendelseType == HendelseType.JOURNALFORING
    fun harEnhet() = enhet != null
    fun harAktorId() = aktorId != null
    fun harJournalpostIdPrefix() = journalpostId.contains("-")
    fun erBidragJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("BID")
    fun erJoarkJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("JOARK")
    fun erInngaaende() = journalposttype == "I"
    fun erUtgaaende() = journalposttype == "U"
    fun hentEndretAvEnhetsnummer() = sporing?.enhetsnummer ?: enhet
    fun hentSaksbehandlerInfo() = sporing?.lagSaksbehandlerInfo() ?: "ukjent saksbehandler"

    constructor(
        journalpostId: String?,
        enhet: String?,
        fagomrade: String?,
        journalstatus: String?,
        correlationId: String?,
        saksbehandlersEnhet: String?
    ) : this(
        journalpostId = journalpostId ?: "na",
        enhet = enhet,
        fagomrade = fagomrade,
        journalstatus = journalstatus,
        sporing = Sporingsdata(correlationId = correlationId)
    ) {
        sporing!!.enhetsnummer = saksbehandlersEnhet
    }
    fun printSummary() = "{journalpostId=$journalpostId,fagomrade=$fagomrade,enhet=$enhet,saksbehandlerEnhet=${sporing?.enhetsnummer},journalstatus=$journalstatus,saker=${sakstilknytninger},dokumentDato=${dokumentDato},journalfortDato=${journalfortDato},tittel=${tittel},behandlingstema=$behandlingstema....}"
}

data class Sporingsdata(
    var correlationId: String? = null,
    var brukerident: String? = null,
    var saksbehandlersNavn: String? = "ukjent saksbehandler",
    var enhetsnummer: String? = null
){

    fun lagSaksbehandlerInfo(saksbehandlerEnhet: String? = null) = if (brukerident == null && saksbehandlersNavn == null) "ukjent saksbehandler" else hentBrukeridentMedSaksbehandler(saksbehandlerEnhet?:enhetsnummer?:"")
    private fun hentBrukeridentMedSaksbehandler(enhetsnummer: String) = "${saksbehandlersNavn?:"Ukjent"} (${brukerident?:"Ukjent"}, $enhetsnummer)"
}
