package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.lang.Long.toHexString
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

@ApiModel(value = "Metadata for registrering av ny journalpost")
data class NyJournalpostCommandDto(
        @ApiModelProperty(value = "Avsenders fornavn") var avsenderFornavn: String? = null,
        @ApiModelProperty(value = "Avsenders etternavn") var avsenderEtternavn: String? = null,
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
        @ApiModelProperty(value = "Id for dtoen") var dtoId: String = toHexString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
) : MedDtoId {
    override fun getBeskrevetDtoId(): String {
        return String.format("{}(gjelder-{}/saksnummer-{})", dtoId, gjelder, saksnummer)
    }
}
