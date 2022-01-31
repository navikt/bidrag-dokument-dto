package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Bestill distribusjon av journalpost")
data class DistribuerJournalpostRequest(
    @Schema(description = "Adresse for hvor brev sendes ved sentral print") var adresse: DistribuerTilAdresse? = null
)

@Schema(description = "Respons etter bestilt distribusjon")
data class DistribuerJournalpostResponse(
    @Schema(description = "Journalpostid for dokument som det ble bestilt distribusjon for") var journalpostId: String,
    @Schema(description = "Bestillingid som unikt identifiserer distribusjonsbestillingen") var bestillingsId: String?
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