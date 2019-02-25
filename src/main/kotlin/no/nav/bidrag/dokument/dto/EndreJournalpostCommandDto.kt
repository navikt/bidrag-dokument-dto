package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.lang.Long.toHexString
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

@ApiModel(value = "Metadata for endring av eksisterende journalpost")
data class EndreJournalpostCommandDto(
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: String? = null,
        @ApiModelProperty(value = "Avsenders fornavn") var avsenderFornavn: String? = null,
        @ApiModelProperty(value = "Avsenders etternavn") var avsenderEtternavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummeret til tilknyttet bidragsak") var saksnummer: EndreSaksnummerDto? = null,
        @ApiModelProperty(value = "Id for dtoen") var dtoId: String = toHexString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
)

@ApiModel(value = "Metadata for endring av saksnummer på en eksisterende journalpost")
data class EndreSaksnummerDto(
        @ApiModelProperty(value = "Saksnummeret som skal tilknyttes journalposten") var saksnummer: String? = null,
        @ApiModelProperty(value = "Legg til som ny journalsak") var erTilknyttetNySak: Boolean = false,
        @ApiModelProperty(value = "Saksnummer som skal erstattes på journalposten") var saksnummerSomSkalErstattes: String? = null
)
