package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "Metadata til en journalpost")
data class JournalpostDto(
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Dokumentene som følger journalposten") var dokumenter: List<DokumentDto> = emptyList(),
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fagområdet for journalposten. BID for bidrag og FAR for farskap") var fagomrade: String? = null,
        @ApiModelProperty(value = "Aktøren for hvem/hva dokumente(t/ne) gjelder") var gjelderAktor: AktorDto? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var innhold: String? = null,
        @ApiModelProperty(value = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journalfortDato: LocalDate? = null,
        @ApiModelProperty(value = "Identifikator av journalpost i midlertidig brevlager eller fra joark på formatet [BID|JOARK]-<journalpostId>") var journalpostId: String? = null,
        @ApiModelProperty(value = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) journalpost; (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Journalpostens status, (A, D, J, M, O, R, S, T, U)") var journalstatus: String? = null,
        @ApiModelProperty(value = "Om journalposten er feilført på bidragssak") var feilfort: Boolean? = null,
        @ApiModelProperty(value = "Brevkoden til en journalpost") var brevkode: KodeDto? = null
)

@ApiModel(value = "Metadata om en aktør")
data class AktorDto(
        @ApiModelProperty(value = "Identifaktor til aktøren") var ident: String = ""
)

@ApiModel(value = "Metadata for et dokument")
data class DokumentDto(
        @ApiModelProperty(value = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@ApiModel(value = "Metadata for endring av en journalpost")
data class EndreJournalpostCommand(
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: String? = null,
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummer til bidragsaker som journalpost skal tilknyttes") var tilknyttSaker: List<String> = emptyList(),
        @ApiModelProperty(value = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
        @ApiModelProperty(value = "Behandlingstema") var behandlingstema: String? = null,
        @ApiModelProperty(value = "Endre fagområde") var fagomrade: String? = null,
        @ApiModelProperty(value = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
        @ApiModelProperty(value = "Tittel på journalposten") var tittel: String? = null,
        @ApiModelProperty(value = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = false
) {
    fun manglerGjelder() = gjelder == null
}

@ApiModel(value = "Metadata for registrering av journalpost")
@Deprecated(message = "will be removed when version 0.10.0 is implemented in bidrag-dokument")
data class RegistrereJournalpostCommand(
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: String? = null,
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Saksnummer til bidragsaker som journalpost skal registreres på") var saksnummer: List<String> = emptyList(),
        @ApiModelProperty(value = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
        @ApiModelProperty(value = "Behandlingstema") var behandlingstema: String? = null,
        @ApiModelProperty(value = "Endre fagområde") var fagomrade: String? = null,
        @ApiModelProperty(value = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
        @ApiModelProperty(value = "Journalførende enhet") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Tittel på journalposten") var tittel: String? = null,
        @ApiModelProperty(value = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = true
)

@ApiModel(value = "Metadata for endring av et dokument")
data class EndreDokument(
        @ApiModelProperty(value = "Brevkoden til dokumentet") var brevkode: String? = null,
        @ApiModelProperty(value = "Identifikator av dokument informasjon") var dokId: Int = -1,
        @ApiModelProperty(value = "Tittel på dokumentet") var tittel: String? = null
)

@ApiModel(value = "Metadata for kode vs dekode i et kodeobjekt")
data class KodeDto(
        @ApiModelProperty(value = "Koden") var kode: String? = null,
        @ApiModelProperty(value = "Dekode (kodebeskrivelse)") var dekode: String? = null,
        @ApiModelProperty(value = "Om kodeobjektet inneholder en gyldig verdi") var erGyldig: Boolean = true
)

@ApiModel(value = "Metadata til en respons etter journalpost med tilhørende data")
data class JournalpostResponse(
        @ApiModelProperty(value = "journalposten som er etterspurt") var journalpost: JournalpostDto? = null,
        @ApiModelProperty(value = "alle saker som journalposten er tilknyttet") var sakstilknytninger: List<String> = emptyList()
)
