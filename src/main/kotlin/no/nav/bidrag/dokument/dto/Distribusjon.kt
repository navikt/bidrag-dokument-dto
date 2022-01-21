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

enum class AdresseType {
    NORSK_POSTADRESSE,
    UTENLANDSK_POSTADRESSE
}

@Schema(description = "Adresse for hvor brev sendes ved sentral print")
data class DistribuerTilAdresse(
    var adresselinje1: String,
    var adresselinje2: String? = null,
    var adresselinje3: String? = null,
    var adressetype: AdresseType,
    var land: String? = null,
    var postnummer: String? = null,
    var poststed: String? = null,
)