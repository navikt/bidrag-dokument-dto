package no.nav.bidrag.dokument.dto

data class DokumentHendelse(
    val dokumentreferanse: String,
    val journalpostId: String? = null,
    val forsendelseId: String? = null,
    val sporingId: String,
    val arkivSystem: DokumentArkivSystem? = null,
    val status: DokumentStatus? = null,
    val hendelseType: DokumentHendelseType
)

enum class DokumentArkivSystem {
    MIDLERTIDLIG_BREVLAGER
}
enum class DokumentHendelseType {
    BESTILLING,
    OPPRETTET,
    SLETTET,
    ENDRING
}
enum class DokumentStatus {
    UNDER_PRODUKSJON,
    FERDIGSTILT,
    SLETTET
}