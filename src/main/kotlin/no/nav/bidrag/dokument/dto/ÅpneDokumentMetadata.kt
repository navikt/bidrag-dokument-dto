package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ÅpneDokumentMetadata(
    @Schema(description = "Link for å åpne dokumentet") val link: String? = null,
    @Schema(description = "Hvilken format dokument er på") val format: DokumentFormatDto,
    @Schema(description = "Status på dokumentet")  val status: DokumentStatusDto
)

enum class DokumentFormatDto {
    PDF,
    MBDOK,
    HTML
}