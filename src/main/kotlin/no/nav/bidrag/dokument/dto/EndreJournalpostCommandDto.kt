package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "Metadata for endring av eksisterende journalpost")
data class EndreJournalpostCommandDto(
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: String? = null,
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummeret til tilknyttet bidragsak") var saksnummer: EndreSaksnummerDto? = null
)

@ApiModel(value = "Metadata for endring av saksnummer på en eksisterende journalpost")
data class EndreSaksnummerDto(
        @ApiModelProperty(value = "Saksnummeret som skal tilknyttes journalposten") var saksnummer: String? = null,
        @ApiModelProperty(value = "Legg til som ny journalsak") var erTilknyttetNySak: Boolean = false,
        @ApiModelProperty(value = "Saksnummer som skal erstattes på journalposten") var saksnummerSomSkalErstattes: String? = null
)
