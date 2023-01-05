package no.nav.bidrag.dokument.dto

enum class DokumentArkivSystemDto {
    JOARK,
    MIDLERTIDLIG_BREVLAGER,
    UKJENT,
    BIDRAG
}

/**
Dokumentstatus gir en indikasjon på hvorvidt dokumentet er ferdigstilt eller under arbeid, eventuelt avbrutt.
 */
enum class DokumentStatusDto {
    IKKE_BESTILT,
    /**
        Bestilling av dokument feilet. Dette kan skje pga ugyldig data eller en midlertidlig feil i systemet
     */
    BESTILLING_FEILET,
    /**
        Bestilling er sendt for dokument og venter på kvittering fra produksjon.
     */
    UNDER_PRODUKSJON,
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