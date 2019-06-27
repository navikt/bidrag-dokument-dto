package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.Optional

@ApiModel(value = "En avvikshendelse som kan utføres på en journalpost")
data class Avvikshendelse(
        @ApiModelProperty(value = "Type avvik") val avvikType: String
) {
    constructor() : this("avvik ikke angitt")
    constructor(avvikType: AvvikType) : this(avvikType = avvikType.name)

    fun hent(): Optional<AvvikType> {
        try {
            return Optional.of(AvvikType.valueOf(avvikType))
        } catch (exception: Exception) {
            return Optional.empty();
        }
    }
}

data class OpprettAvvikshendelseResponse(
        @ApiModelProperty(value = "Type avvik") var avvikType: String,
        @ApiModelProperty(value = "Oppgave id for oppgaven som ble opprettet på bakgrunn av avviket") var oppgaveId: Long? = null,
        @ApiModelProperty(value = "Enhetsnummer til enheten som oppgaven er tildelt") var tildeltEnhetsnr: String? = null,
        @ApiModelProperty(value = "Oppgavens tema") var tema: String? = null,
        @ApiModelProperty(value = "Oppgavens type") var oppgavetype: String? = null
) {
    constructor() : this("avvik ikke angitt")
    constructor(avvikType: AvvikType) : this(avvikType.name)
}

enum class AvvikType {
    BESTILL_ORGINAL,
    BESTILL_RESKANNING
}
