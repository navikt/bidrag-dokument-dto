package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Metadata for en url til et fysisk dokument")
data class DokumentUrlDto(
        @ApiModelProperty(value = "url til et fysisk dokument") var dokumentUrl: String? = null,
        @ApiModelProperty(value = "type system som er ansvarlig for dokumentet, eks: BREVLAGER") var type: String? = null
)

@ApiModel(value = "Metadata for en forespørsel om tilgang til et dokument")
data class DokumentTilgangRequest(
        @ApiModelProperty(value = "referansen til dokumentet det ønskes tilgang til") var dokumentReferanse: String? = null,
        @ApiModelProperty(value = "saksbehandleren som ønsker tilgang") var saksbehandler: String? = null
)
