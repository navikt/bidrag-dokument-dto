package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Metadata for en bidragssak")
data class BidragSakPipDto(
    @Schema(description = "Saksnummeret til bidragssaken") var saksnummer: String? = null,
    @Schema(description = "Om saken omhandler paragraf 19") var erParagraf19: Boolean = false,
    @Schema(description = "Fødselsnummer til personer innvolvert i bidragssaken") var roller: List<String> = emptyList()
)
