package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
	
@ApiModel(value = "Metadata for en bidragssak")
data class BidragSakPipDto(
        @ApiModelProperty(value = "Saksnummeret til bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Om saken omhandler paragraf 19") var erParagraf19: Boolean = false,
        @ApiModelProperty(value = "Fødselsnummer til personer innvolvert i bidragssaken") var roller: List<String> = emptyList()
)
