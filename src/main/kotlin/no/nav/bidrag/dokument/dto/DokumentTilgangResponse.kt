package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Metadata for en url til et fysisk dokument")
data class DokumentTilgangResponse(
    @Schema(description = "url til et fysisk dokument") var dokumentUrl: String = "",
    @Schema(description = "type system som er ansvarlig for dokumentet, eks: BREVLAGER") var type: String = ""
)
