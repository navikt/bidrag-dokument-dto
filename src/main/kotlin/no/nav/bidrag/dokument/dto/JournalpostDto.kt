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
        @ApiModelProperty(value = "Saksnummer på bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Liste over saker hvor journalpostens sin gjelderBrukerId er representert (når det er en person)") var bidragssaker: List<BidragSakDto> = emptyList()
)

@ApiModel(value = "Dokument metadata")
data class DokumentDto(
        @ApiModelProperty(value = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@ApiModel(value = "Metadata om en bidragssak")
data class BidragSakDto(
        @ApiModelProperty(value = "Eierfogd for bidragssaken") var eierfogd: String? = null,
        @ApiModelProperty(value = "Saksnummeret til bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Saksstatus til bidragssaken") var saksstatus: String? = null,
        @ApiModelProperty(value = "Kategorikode: 'N' eller 'U'") var kategori: String? = null,
        @ApiModelProperty(value = "Om saken omhandler paragraf 19") var erParagraf19: Boolean = false,
        @ApiModelProperty(value = "Rollene som saken inneholder") var roller: List<RolleDto> = emptyList()
)

@ApiModel(value = "Metadata om en rolle i en bidraggsak (når rollen er en person)")
data class RolleDto(
        @ApiModelProperty(value = "Fødselsnummer til en person i en bidragssak") var foedselsnummer: String? = null,
        @ApiModelProperty(value = "Rolletypen til en person i en bidragssak, f.eks: BM eller BP (bidragsmottaker eller bidragspliktig)") var rolleType: String? = null
)
