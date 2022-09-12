package no.nav.bidrag.dokument.dto

import java.time.LocalDate

data class JournalpostHendelse(
    var journalpostId: String = "na",
    var aktorId: String? = null,
    var fnr: String? = null,
    var tittel: String? = null,
    var fagomrade: String? = null,
    var enhet: String? = null,
    var journalstatus: String? = null,
    var sporing: Sporingsdata? = null,
    var sakstilknytninger: List<String>? = emptyList(),
    var dokumentDato: LocalDate? = null,
    var journalfortDato: LocalDate? = null,
) {

    fun harEnhet() = enhet != null
    fun harAktorId() = aktorId != null
    fun harJournalpostIdPrefix() = journalpostId.contains("-")
    fun harJournalpostIdBIDPrefix() = harJournalpostIdPrefix() && journalpostId.startsWith("BID")
    fun erBidragJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("BID")
    fun erJoarkJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("JOARK")
    fun hentEndretAvEnhetsnummer() = if (sporing?.enhetsnummer != null) sporing!!.enhetsnummer else enhet
    fun hentSaksbehandlerInfo() = if (sporing != null) sporing!!.lagSaksbehandlerInfo(sporing?.enhetsnummer) else "ukjent saksbehandler"
    val erMottattStatus get() = Journalstatus.MOTTATT == journalstatus
    val erEksterntFagomrade get() = fagomrade != null && (fagomrade != Fagomrade.BIDRAG && fagomrade != Fagomrade.FARSKAP)
    val journalpostIdUtenPrefix get() = if (harJournalpostIdPrefix()) journalpostId.split('-')[1] else journalpostId
    val journalpostMedBareBIDprefix get() = if (harJournalpostIdBIDPrefix()) journalpostId else journalpostIdUtenPrefix

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
    var saksbehandlersNavn: String? = null,
    var enhetsnummer: String? = null
){

    internal fun lagSaksbehandlerInfo(enhetsnummer: String?) = if (brukerident == null) "ukjent saksbehandler" else hentBrukeridentMedSaksbehandler(enhetsnummer)
    private fun hentBrukeridentMedSaksbehandler(enhetsnummer: String?) = if (saksbehandlersNavn == null) brukerident!! else "$saksbehandlersNavn ($brukerident, ${enhetsnummer?:""})"
}
