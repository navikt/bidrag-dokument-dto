package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

data class DokumentMetadata(
    @Schema(description = "Journalpostid med arkiv prefiks som skal benyttes når dokumentet hentes") val journalpostId: String? = null,
    val dokumentreferanse: String? = null,
    @Schema(description = "Hvilken format dokument er på. Dette forteller hvordan dokumentet må åpnes.") val format: DokumentFormatDto,
    @Schema(description = "Status på dokumentet") val status: DokumentStatusDto,
    @Schema(description = "Hvilken arkivsystem dokumentet er lagret på") val arkivsystem: DokumentArkivSystemDto
)

enum class DokumentFormatDto {
    PDF,
    MBDOK,
    HTML
}