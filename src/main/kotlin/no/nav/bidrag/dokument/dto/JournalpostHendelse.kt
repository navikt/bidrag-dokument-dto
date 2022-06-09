package no.nav.bidrag.dokument.dto

data class JournalpostHendelse(
    var journalpostId: String = "na",
    var aktorId: String? = null,
    var fnr: String? = null,
    var fagomrade: String? = null,
    var enhet: String? = null,
    var journalstatus: String? = null,
    var sporing: Sporingsdata? = null
) {

    internal fun harEnhet() = enhet != null
    internal fun harAktorId() = aktorId != null
    internal fun harJournalpostIdPrefix() = journalpostId.contains("-")
    internal fun erBidragJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("BID")
    internal fun erJoarkJournalpost() = harJournalpostIdPrefix() && journalpostId.startsWith("JOARK")
    internal fun hentEndretAvEnhetsnummer() = if (sporing?.enhetsnummer != null) sporing!!.enhetsnummer else enhet
    internal fun hentSaksbehandlerInfo() = if (sporing != null) sporing!!.lagSaksbehandlerInfo() else "ukjent saksbehandler"
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
}

data class Sporingsdata(
    var correlationId: String? = null,
    var brukerident: String? = null,
    var saksbehandlersNavn: String? = null,
    var enhetsnummer: String? = null
){
    internal fun lagSaksbehandlerInfo() = if (brukerident == null) "ukjent saksbehandler" else hentBrukeridentMedSaksbehandler()
    private fun hentBrukeridentMedSaksbehandler() = if (saksbehandlersNavn == null) brukerident!! else "$brukerident, $saksbehandlersNavn"
}
