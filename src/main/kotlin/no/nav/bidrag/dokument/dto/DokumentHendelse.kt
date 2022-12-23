package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema

data class DokumentHendelse(
    val dokumentreferanse: String,
    val journalpostId: String? = null,
    val forsendelseId: String? = null,
    val sporingId: String,
    val arkivSystem: DokumentArkivSystemTo? = null,
    val status: DokumentStatusTo? = null,
    val hendelseType: DokumentHendelseType
)

enum class DokumentArkivSystemTo {
    MIDLERTIDLIG_BREVLAGER
}
enum class DokumentHendelseType {
    /**
        Bestilling av et nytt dokument
     */
    BESTILLING,
    /**
        Kvittering på at bestilling er mottatt og blir prosessert
     */
    BESTILLING_PROSESSERES,
    /**
        Dokument under redigering ble avbrutt
    */
    AVBRUTT,
    /**
        Dokumentet ble endret. Brukes når en redigerbar dokument ble lagret av bruker.
     */
    ENDRING,
    FERDIGSTILT
}

/**
    Dokumentstatus gir en indikasjon på hvorvidt dokumentet er ferdigstilt eller under arbeid, eventuelt avbrutt.
 */
enum class DokumentStatusTo {
    /**
        Dokumentet er under arbeid. Benyttes for redigerbare brev.
     */
    UNDER_REDIGERING,
    /**
        Dokumentet er ferdigstilt. Dokumentet er låst for endringer
     */
    FERDIGSTILT,
    /**
        Dokumentet ble opprettet, men ble avbrutt under redigering. Benyttes for redigerbare brev.
    */
    AVBRUTT
}