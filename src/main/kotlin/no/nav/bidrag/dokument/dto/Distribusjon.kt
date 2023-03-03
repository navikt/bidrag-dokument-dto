package no.nav.bidrag.dokument.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Bestill distribusjon av journalpost")
data class DistribuerJournalpostRequest(
    @Schema(description = "Identifiserer batch som forsendelsen inngår i. Brukes for sporing") val batchId: String? = null,
    @Schema(description = "Forsendelsen er skrevet ut og distribuert lokalt. Distribusjon registreres men ingen distribusjon bestilles.") val lokalUtskrift: Boolean = false,
    @Schema(description = "Adresse for hvor brev sendes ved sentral print") val adresse: DistribuerTilAdresse? = null
)

@Schema(description = "Respons etter bestilt distribusjon")
data class DistribuerJournalpostResponse(
    @Schema(description = "Journalpostid for dokument som det ble bestilt distribusjon for") val journalpostId: String,
    @Schema(description = "Bestillingid som unikt identifiserer distribusjonsbestillingen. Vil være null hvis ingen distribusjon er bestilt.") val bestillingsId: String?
)

@Schema(description = "Adresse for hvor brev sendes ved sentral print")
data class DistribuerTilAdresse(
    var adresselinje1: String? = null,
    var adresselinje2: String? = null,
    var adresselinje3: String? = null,
    @Schema(description = "ISO 3166-1 alpha-2 to-bokstavers landkode")
    var land: String? = null,
    var postnummer: String? = null,
    var poststed: String? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DistribusjonInfoDto(
    val journalstatus: String,
    val kanal: Kanal,
    val utsendingsinfo: UtsendingsInfo? = null
)

enum class UtsendingsInfo(
    val adresse: String? = null,
    val tittel: String? = null,
    val varslingstekst: String? = null
)
