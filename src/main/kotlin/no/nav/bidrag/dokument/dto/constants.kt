package no.nav.bidrag.dokument.dto

const val FARSKAP_UTELUKKET_PREFIKS = "FARSKAP UTELUKKET"

object BidragEnhet {
    const val ENHET_FARSKAP = "4860"
    const val ENHET_UTLAND = "4865"
}

enum class JournalpostStatus {
    MOTTATT,
    JOURNALFØRT,
    EKSPEDERT,
    DISTRIBUERT,
    AVBRUTT,
    KLAR_FOR_DISTRIBUSJON,
    RETUR,
    FERDIGSTILT,
    FEILREGISTRERT,
    RESERVERT,
    UTGÅR,
    UNDER_OPPRETTELSE,
    UNDER_PRODUKSJON,
    UKJENT;

    companion object {
        fun konverterStatus(status: String?): JournalpostStatus? {
            return when (status) {
                MOTTATT.name, Journalstatus.MOTTATT -> MOTTATT
                FEILREGISTRERT.name, Journalstatus.FEILREGISTRERT -> FEILREGISTRERT
                UNDER_PRODUKSJON.name, Journalstatus.UNDER_PRODUKSJON -> UNDER_PRODUKSJON
                UNDER_OPPRETTELSE.name, Journalstatus.UNDER_OPPRETTELSE -> UNDER_OPPRETTELSE
                FERDIGSTILT.name, Journalstatus.FERDIGSTILT -> FERDIGSTILT
                EKSPEDERT.name, Journalstatus.EKSPEDERT -> EKSPEDERT
                AVBRUTT.name, Journalstatus.AVBRUTT -> AVBRUTT
                KLAR_FOR_DISTRIBUSJON.name, Journalstatus.KLAR_TIL_PRINT -> KLAR_FOR_DISTRIBUSJON
                UTGÅR.name, Journalstatus.UTGAR -> UTGÅR
                RETUR.name, Journalstatus.RETUR -> RETUR
                RESERVERT.name, Journalstatus.RESERVERT -> RESERVERT
                JOURNALFØRT.name, Journalstatus.JOURNALFORT -> JOURNALFØRT
                DISTRIBUERT.name -> DISTRIBUERT
                else -> null
            }
        }
    }
}

