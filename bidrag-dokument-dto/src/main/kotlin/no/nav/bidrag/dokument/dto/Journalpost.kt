package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "Metadata for en journalpost, no.nav.bidrag.dokument::bidrag-dokument-dto")
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
        @ApiModelProperty(value = "Journalpostens status, (A, D, J, M, O, R, T, U)") var journalstatus: String? = null,
        @ApiModelProperty(value = "Om journalposten er feilført på bidragssak") var feilfort: Boolean? = null,
        @ApiModelProperty(value = "Brevkoden til en journalpost") var brevkode: KodeDto? = null
)

@ApiModel(value = "Metadata om en aktør")
data class AktorDto(
        @ApiModelProperty(value = "Identifaktor til aktøren") var ident: String = ""
)

@ApiModel(value = "Dokument metadata")
data class DokumentDto(
        @ApiModelProperty(value = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@ApiModel(value = "Metadata for registrering av ny journalpost")
data class NyJournalpostCommand(
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentdato: LocalDate? = null,
        @ApiModelProperty(value = "Dokumentreferanse for det arkiverte dokumentet") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
        @ApiModelProperty(value = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = LocalDate.now(),
        @ApiModelProperty(value = "Saksnummeret til tilknyttet bidragsak") var saksnummer: String? = null,
        @ApiModelProperty(value = "Fagområde journalposten tilhører for bidragssaker. 'BNR' = bidrag, 'FAR' = farskap") var fagomrade: String? = null,
        @ApiModelProperty(value = "Behandlingstema") var behandlingstema: String? = null,
        @ApiModelProperty(value = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
        @ApiModelProperty(value = "Tittel på journalposten") var tittel: String? = null,
        @ApiModelProperty(value = "En liste over endringer i dokumenter") var dokumenter: List<OpprettDokument> = emptyList()
)

data class OpprettDokument (
        @ApiModelProperty(value = "Brevkoden til dokumentet") var brevkode: String? = null,
        @ApiModelProperty(value = "Dokumentets kategori, for eksempel SOK (soknad), SED eller FORVALTNINGSNOTAT") var dokumentKategori: String? = null,
        @ApiModelProperty(value = "Tittel på dokumentet") var tittel: String? = null
)

@ApiModel(value = "Metadata for endring av eksisterende journalpost")
data class EndreJournalpostCommand(
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: String? = null,
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummeret til tilknyttet bidragsak") var saksnummer: EndreSaksnummer? = null,
        @ApiModelProperty(value = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
        @ApiModelProperty(value = "Behandlingstema") var behandlingstema: String? = null,
        @ApiModelProperty(value = "Endre fagområde") var fagomrade: String? = null,
        @ApiModelProperty(value = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
        @ApiModelProperty(value = "Journalførende enhet") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Tittel på journalposten") var tittel: String? = null
)

@ApiModel(value = "Metadata for endring av saksnummer på en eksisterende journalpost")
data class EndreSaksnummer(
        @ApiModelProperty(value = "Saksnummeret som skal tilknyttes journalposten") var saksnummer: String? = null,
        @ApiModelProperty(value = "Legg til som ny journalsak") var erTilknyttetNySak: Boolean = false,
        @ApiModelProperty(value = "Saksnummer som skal erstattes på journalposten") var saksnummerSomSkalErstattes: String? = null
)

data class EndreDokument(
        @ApiModelProperty(value = "Brevkoden til dokumentet") var brevkode: String? = null,
        @ApiModelProperty(value = "Identifikator av dokument informasjon") var dokId: Int = -1,
        @ApiModelProperty(value = "Tittel på dokumentet") var tittel: String? = null
)

@ApiModel(value = "Metadata for kode/dekode")
data class KodeDto(
        @ApiModelProperty(value = "Koden") var kode: String? = null,
        @ApiModelProperty(value = "Dekode (kodebeskrivelse)") var dekode: String? = null,
        @ApiModelProperty(value = "Om kodeobjektet inneholder en gyldig verdi") var erGyldig: Boolean = true
)
