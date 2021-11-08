package no.nav.bidrag.dokument.dto

data class JournalpostHendelse(
    var journalpostId: String = "na",
    var aktorId: String? = null,
    var fagomrade: String? = null,
    var enhet: String? = null,
    var journalstatus: String? = null,
    var sporing: Sporingsdata? = null
) {

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
)
