package no.nav.bidrag.dokument.dto

import java.time.LocalDate

enum class HendelseType {
    JOURNALFORING,
    ENDRING
}
data class JournalpostHendelse(
    var journalpostId: String = "na",
    var aktorId: String? = null,
    var fnr: String? = null,
    var behandlingstema: String? = null,
    var tittel: String? = null,
    var fagomrade: String? = null,
    var journalposttype: String? = null,
    var hendelseType: HendelseType? = null,
    var enhet: String? = null,
    var journalstatus: String? = null,
    var sporing: Sporingsdata? = null,
    var sakstilknytninger: List<String>? = emptyList(),
    var dokumentDato: LocalDate? = null,
    var journalfortDato: LocalDate? = null,
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
    fun printSummary() = "{journalpostId=$journalpostId,fagomrade=$fagomrade,enhet=$enhet,saksbehandlerEnhet=${sporing?.enhetsnummer},journalstatus=$journalstatus,saker=${sakstilknytninger},dokumentDato=${dokumentDato},journalfortDato=${journalfortDato},tittel=${tittel}....}"
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
