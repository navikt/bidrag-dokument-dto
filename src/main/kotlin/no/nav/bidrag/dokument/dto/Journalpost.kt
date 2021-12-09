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
    @Schema(description = "Kanalen som er kilden til at journalposten ble registrert") var kilde: Kanal? = null,
    @Schema(description = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
    @Schema(description = "Inngående (I), utgående (U) journalpost; (X) internt notat") var dokumentType: String? = null,
    @Schema(description = "Journalpostens status, (A, D, J, M, O, R, S, T, U)") var journalstatus: String? = null,
    @Schema(description = "Om journalposten er feilført på bidragssak") var feilfort: Boolean? = null,
    @Schema(description = "Brevkoden til en journalpost") var brevkode: KodeDto? = null,
    @Schema(description = "Informasjon om returdetaljer til journalpost") var returDetaljer: ReturDetaljer? = null,
    @Schema(description = "Joark journalpostid for bidrag journalpost som er arkivert i Joark") var joarkJournalpostId: String? = null
    )

@Schema(description = "Metadata for retur detaljer")
data class ReturDetaljer(
    @Schema(description = "Dato for siste retur") var dato: LocalDate? = null,
    @Schema(description = "Totalt antall returer") var antall: Int? = null,
    @Schema(description = "Liste med logg av alle registrerte returer") var logg: List<ReturDetaljerLog>? = emptyList(),

    )

@Schema(description = "Metadata for retur detaljer log")
data class ReturDetaljerLog(
    @Schema(description = "Dato for retur") var dato: LocalDate? = null,
    @Schema(description = "Beskrivelse av retur (eks. addresse forsøkt sendt)") var beskrivelse: String? = null,
)

@Schema(description = "Metadata om en aktør")
data class AktorDto(
    @Schema(description = "Identifaktor til aktøren") var ident: String = "",
    @Schema(description = "Hvilken identtype som skal brukes") var type: String? = null
) {
    fun hentIdentType(): IdentType? = if (type == null) null else try {
        IdentType.valueOf(type!!)
    } catch (e: Exception) {
        null
    }
}

@Schema(description = "Identtypene til en aktør")
enum class IdentType {
    @Schema(description = "AktoerId (nav sitt løpenummer)")
    AKTOERID,

    @Schema(description = "Fødselsnummer")
    FNR,

    @Schema(description = "Organisasjonsnummer ")
    ORGNR
}

@Schema(description = "Metadata for et dokument")
data class DokumentDto(
    @Schema(description = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
    @Schema(description = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@Schema(description = "Journalpostens ble mottatt i kanal")
enum class Kanal {
    @Schema(description = "Ditt NAV (Innsending bidrag)")
    NAV_NO_BID,

    @Schema(description = "Skanning Bidrag")
    SKAN_BID,

    @Schema(description = "Ditt NAV")
    NAV_NO,

    @Schema(description = "Skanning Nwra")
    SKAN_NETS,
}

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
    @Schema(description = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = false,
    @Schema(description = "Liste med retur detaljer som skal endres") var endreReturDetaljer: List<EndreReturDetaljer>? = null
    ) {
    @Suppress("unused")
    fun manglerGjelder() = gjelder == null
}

@Schema(description = "Metadata for endring av et retur detalj")
data class EndreReturDetaljer (
    @Schema(description = "Dato på retur detaljer som skal endres") var originalDato: LocalDate,
    @Schema(description = "Ny dato på retur detaljer") var nyDato: LocalDate? = null,
    @Schema(description = "Beskrivelse av retur (eks. addresse forsøkt sendt)") var beskrivelse: String,
)

@Schema(description = "Metadata for endring av et dokument")
data class EndreDokument(
    @Schema(description = "Brevkoden til dokumentet") var brevkode: String? = null,
    @Schema(description = "Identifikator av dokument informasjon") var dokId: Long = -1,
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
