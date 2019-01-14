package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModelProperty

data class BidragSakDto(
        @ApiModelProperty(value = "Saksnummeret til bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Om saken omhandler paragraf 19") var erParagraf19: Boolean = false,
        @ApiModelProperty(value = "Rollene som saken inneholder") var roller: List<RolleDto> = emptyList()
)

data class RolleDto(
        @ApiModelProperty(value = "Fødselsnummer til person i bidragssak") var foedselsnummer: String? = null,
        @ApiModelProperty(value = "Rolletypen til person i bidragssak") var rolleType: String? = null
)
