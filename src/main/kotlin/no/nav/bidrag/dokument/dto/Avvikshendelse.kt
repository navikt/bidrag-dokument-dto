package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.Optional

@ApiModel(value = "En avvikshendelse som kan utføres på en journalpost")
data class Avvikshendelse(
        @ApiModelProperty(value = "Type avvik") var avvikType: String? = null,
        @ApiModelProperty(value = "Enhetsnummer for gjeldense avvik, eks: sendes til i BESTILL_ORIGINAL") var enhetsnummer: String? = null
) {
    fun hent(): Optional<AvvikType> {
        try {
            if (avvikType == null) {
                return Optional.empty()
            }

            return Optional.of(AvvikType.valueOf(avvikType!!))
        } catch (exception: Exception) {
            return Optional.empty()
        }
    }
}

data class OpprettAvvikshendelseResponse(
        @ApiModelProperty(value = "Type avvik") var avvikType: String? = null,
        @ApiModelProperty(value = "Oppgave id for oppgaven som ble opprettet på bakgrunn av avviket") var oppgaveId: Long? = null,
        @ApiModelProperty(value = "Enhetsnummer til enheten som oppgaven er tildelt") var tildeltEnhetsnr: String? = null,
        @ApiModelProperty(value = "Oppgavens tema") var tema: String? = null,
        @ApiModelProperty(value = "Oppgavens type") var oppgavetype: String? = null
) {
    constructor(avvikType: AvvikType) : this(avvikType.name, null,  null, null, null)
}

enum class AvvikType {
    BESTILL_ORIGINAL,
    BESTILL_RESKANNING
}
