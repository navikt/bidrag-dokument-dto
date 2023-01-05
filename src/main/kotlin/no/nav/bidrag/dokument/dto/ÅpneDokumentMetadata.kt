package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ÅpneDokumentMetadata(
    @Schema(description = "Link for å åpne dokumentet") val link: String? = null,
    val journalpostId: String? = null,
    val dokumentreferanse: String? = null,
    @Schema(description = "Hvilken format dokument er på. Dette forteller hvordan dokumentet bør åpnes.") val format: DokumentFormatDto,
    @Schema(description = "Status på dokumentet") val status: DokumentStatusDto,
    @Schema(description = "Hvilken arkivsystem dokumentet er lagret på") val arkivsystem: DokumentArkivSystemDto
)

enum class DokumentFormatDto {
    PDF,
    MBDOK,
    HTML
}