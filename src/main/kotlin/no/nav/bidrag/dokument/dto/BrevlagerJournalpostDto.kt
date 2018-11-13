package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(value = "Metadata for en journalpost hentet fra midlertidig brevlager")
data class BrevlagerJournalpostDto(
        @ApiModelProperty(value = "Avsenders navn eller fornavn dersom navn ikke er kjent") var avsender: String? = null,
        @ApiModelProperty(value = "Avsenders fornavn") var avsenderFornavn: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentdato: LocalDate? = null,
        @ApiModelProperty(value = "Dokumentreferanse for det arkiverte dokumentet") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
        @ApiModelProperty(value = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
        @ApiModelProperty(value = "Identifikator av journalpost") var journalpostId: Int? = null,
        @ApiModelProperty(value = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummeret til bidragsaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Fagområdet for denne journalposten. Alltid BID (Bidrag)") val fagomrade: String = "BID",
        @ApiModelProperty(value = "Dato da dokument ble skannet") var skannetDato: LocalDate? = null
)
