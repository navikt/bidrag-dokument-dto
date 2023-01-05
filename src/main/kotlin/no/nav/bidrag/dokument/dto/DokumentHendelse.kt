package no.nav.bidrag.dokument.dto

data class DokumentHendelse(
    val dokumentreferanse: String,
    val journalpostId: String? = null,
    val forsendelseId: String? = null,
    val sporingId: String,
    val arkivSystem: DokumentArkivSystemDto? = null,
    val status: DokumentStatusDto? = null,
    val hendelseType: DokumentHendelseType
)
enum class DokumentHendelseType {
    /**
        Bestilling av et nytt dokument
     */
    BESTILLING,
    /**
        Dokument under redigering ble avbrutt
    */
    AVBRUTT,
    /**
        Dokumentet ble endret. Brukes når en redigerbar dokument blir lagret av bruker.
     */
    ENDRING,
    /**
        Dokumentet ble ferdigstilt og er dermed låst for endringer.
     */
    FERDIGSTILT
}