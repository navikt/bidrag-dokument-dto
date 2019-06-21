package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "En avvikshendelse som kan utføres på en journalpost")
abstract class Avvikshendelse(
        @ApiModelProperty(value = "Type avvik") val avvikType: String
)

@ApiModel(value = "Bestill orginalt scannet dokument for en journalpost")
data class BestillOrginal(
        private val avvik: AvvikType = AvvikType.BESTILL_ORGINAL
) : Avvikshendelse(
        avvikType = avvik.name
)

enum class AvvikType {
    BESTILL_ORGINAL
}
