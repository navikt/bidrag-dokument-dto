package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Metadata for en url til et fysisk dokument")
data class DokumentUrlDto(
        @ApiModelProperty(value = "url til et fysisk dokument") var dokumentUrl: String? = null,
        @ApiModelProperty(value = "type system som er ansvarlig for dokumentet, eks: BREVLAGER") var type: String? = null
)
