package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Metadata til en journalpost")
data class JournalpostDto(
    @Schema(description = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
    @Schema(description = "Dokumentene som følger journalposten") var dokumenter: List<DokumentDto> = emptyList(),
    @Schema(description = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
    @Schema(description = "Fagområdet for journalposten. BID for bidrag og FAR for farskap") var fagomrade: String? = null,
    @Schema(description = "Aktøren for hvem/hva dokumente(t/ne) gjelder") var gjelderAktor: AktorDto? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var innhold: String? = null,
    @Schema(description = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
    @Schema(description = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
    @Schema(description = "Dato dokument ble journalført") var journalfortDato: LocalDate? = null,
    @Schema(description = "Identifikator av journalpost i midlertidig brevlager eller fra joark på formatet [BID|JOARK]-<journalpostId>") var journalpostId: String? = null,
    @Schema(description = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
    @Schema(description = "Inngående (I), utgående (U) journalpost; (X) internt notat") var dokumentType: String? = null,
    @Schema(description = "Journalpostens status, (A, D, J, M, O, R, S, T, U)") var journalstatus: String? = null,
    @Schema(description = "Om journalposten er feilført på bidragssak") var feilfort: Boolean? = null,
    @Schema(description = "Brevkoden til en journalpost") var brevkode: KodeDto? = null
)

@Schema(description = "Metadata om en aktør")
data class AktorDto(
    @Schema(description = "Identifaktor til aktøren") var ident: String = ""
)

@Schema(description = "Metadata for et dokument")
data class DokumentDto(
    @Schema(description = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
    @Schema(description = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@Schema(description = "Metadata for endring av en journalpost")
data class EndreJournalpostCommand(
    @Schema(description = "Identifikator av journalpost") var journalpostId: String? = null,
    @Schema(description = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
    @Schema(description = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
    @Schema(description = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
    @Schema(description = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
    @Schema(description = "Saksnummer til bidragsaker som journalpost skal tilknyttes") var tilknyttSaker: List<String> = emptyList(),
    @Schema(description = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
    @Schema(description = "Behandlingstema") var behandlingstema: String? = null,
    @Schema(description = "Endre fagområde") var fagomrade: String? = null,
    @Schema(description = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
    @Schema(description = "Tittel på journalposten") var tittel: String? = null,
    @Schema(description = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = false
) {
    @Suppress("unused")
    fun manglerGjelder() = gjelder == null
}

@Schema(description = "Metadata for registrering av journalpost")
@Deprecated(message = "will be removed when version 0.10.0 is implemented in bidrag-dokument")
data class RegistrereJournalpostCommand(
    @Schema(description = "Identifikator av journalpost") var journalpostId: String? = null,
    @Schema(description = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
    @Schema(description = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
    @Schema(description = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
    @Schema(description = "Saksnummer til bidragsaker som journalpost skal registreres på") var saksnummer: List<String> = emptyList(),
    @Schema(description = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
    @Schema(description = "Behandlingstema") var behandlingstema: String? = null,
    @Schema(description = "Endre fagområde") var fagomrade: String? = null,
    @Schema(description = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
    @Schema(description = "Journalførende enhet") var journalforendeEnhet: String? = null,
    @Schema(description = "Tittel på journalposten") var tittel: String? = null,
    @Schema(description = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = true
)

@Schema(description = "Metadata for endring av et dokument")
data class EndreDokument(
    @Schema(description = "Brevkoden til dokumentet") var brevkode: String? = null,
    @Schema(description = "Identifikator av dokument informasjon") var dokId: Int = -1,
    @Schema(description = "Tittel på dokumentet") var tittel: String? = null
)

@Schema(description = "Metadata for kode vs dekode i et kodeobjekt")
data class KodeDto(
    @Schema(description = "Koden") var kode: String? = null,
    @Schema(description = "Dekode (kodebeskrivelse)") var dekode: String? = null,
    @Schema(description = "Om kodeobjektet inneholder en gyldig verdi") var erGyldig: Boolean = true
)

@Schema(description = "Metadata til en respons etter journalpost med tilhørende data")
data class JournalpostResponse(
    @Schema(description = "journalposten som er etterspurt") var journalpost: JournalpostDto? = null,
    @Schema(description = "alle saker som journalposten er tilknyttet") var sakstilknytninger: List<String> = emptyList()
)
