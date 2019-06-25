package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "En avvikshendelse som kan utføres på en journalpost")
data class Avvikshendelse(
        @ApiModelProperty(value = "Type avvik") val avvikType: String
) {
    constructor() : this("avvik ikke angitt")
    constructor(avvikType: AvvikType) : this(avvikType = avvikType.name)
}

data class OpprettAvvikshendelseResponse(
        @ApiModelProperty(value = "Type avvik") val avvikType: String,
        @ApiModelProperty(value = "Beskrivelse av opprettet avvik") var beskrivelse: String? = null
) {
    constructor() : this("avvik ikke angitt")
    constructor(avvikType: AvvikType) : this(avvikType = avvikType.name)
}

enum class AvvikType {
    BESTILL_ORGINAL
}
