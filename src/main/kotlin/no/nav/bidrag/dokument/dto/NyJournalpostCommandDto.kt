package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

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
        @ApiModelProperty(value = "Correlation Id for logging") var correlationId: String? = null
) : CorrelationIdGenerator {
    override fun correlationId(): String? {
        return correlationId
    }

    override fun payloadId(): String {
        return "gjelder=" + gjelder
    }
}
