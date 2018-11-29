package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "Metadata for en journalpost, no.nav.bidrag.dokument::bidrag-dokument-dto")
data class JournalpostDto(
        @ApiModelProperty(value = "Avsenders etternavn eller fornavn dersom etternavn ikke er kjent") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Dokumentene som følger journalposten") var dokumenter: List<DokumentDto> = emptyList(),
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fagområdet for journalposten. BID for bidrag og FAR for farskap") var fagomrade: String? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumente(t/ne) gjelder") var gjelderBrukerId: List<String> = emptyList(),
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var innhold: String? = null,
        @ApiModelProperty(value = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journalfortDato: LocalDate? = null,
        @ApiModelProperty(value = "Identifikator av journalpost i midlertidig brevlager eller fra joark på formatet [BID|JOARK]-<journalpostId>") var journalpostId: String? = null,
        @ApiModelProperty(value = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummer på format med [GSAK|BID]-{saksnummer} for å si om saken kommer fra GSAK eller BISYS") var saksnummer: String? = null
)

@ApiModel(value = "Dokument metadata")
data class DokumentDto(
        @ApiModelProperty(value = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var tittel: String? = null
)
