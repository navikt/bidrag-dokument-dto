@file:Suppress("unused")

package no.nav.bidrag.dokument.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.Optional

@Schema(description = "En avvikshendelse som kan utføres på en journalpost")
data class Avvikshendelse(
    @Schema(description = "Type avvik") var avvikType: String,
    @Schema(description = "Manuell beskrivelse av avvik") var beskrivelse: String? = null,
    @Schema(description = "Eventuelle detaljer som skal følge avviket") var detaljer: Map<String, String> = HashMap(),
    @Schema(description = "Saksnummer til sak når journalpost er journalført") var saksnummer: String?,
    @Schema(description = "Addresse som skal brukes ved bestilling av ny distribusjon av utgående journalpost. Benyttes ved avvik BESTILL_NY_DISTRIBUSJON") var adresse: DistribuerTilAdresse?
) {
    constructor()
            : this(avvikType = "avvik ikke angitt", beskrivelse = null, saksnummer = null, adresse = null)

    constructor(avvikType: String, enhetsnummer: String)
            : this(avvikType, detaljer = mapOf(Pair("enhetsnummer", enhetsnummer)), beskrivelse = null, saksnummer = null, adresse = null)
    constructor(avvikType: String, beskrivelse: String?, detaljer: Map<String, String>, saksnummer: String?)
            : this(avvikType, beskrivelse, detaljer, saksnummer, null)
    constructor(avvikType: String, detaljer: Map<String, String>, saksnummer: String?)
            : this(avvikType, null, detaljer, saksnummer, null)

    constructor(avvikType: String, enhetsnummer: String, saksnummer: String?)
            : this(avvikType, detaljer = mapOf(Pair("enhetsnummer", enhetsnummer)), beskrivelse = null, saksnummer = saksnummer, adresse = null)

    fun hent(): Optional<AvvikType> {
        val funnetAvvikstype = AvvikType.values()
            .filter { it.name == avvikType }

        return if (funnetAvvikstype.size == 1) {
            Optional.of(funnetAvvikstype[0])
        } else {
            Optional.empty()
        }
    }
}

@Schema(description = "Responsen til en avvikshendelse")
data class BehandleAvvikshendelseResponse(
    @Schema(description = "Type avvik") var avvikType: String,
    @Schema(description = "Oppgave id for oppgaven som ble opprettet på bakgrunn av avviket") var oppgaveId: Long? = null,
    @Schema(description = "Enhetsnummer til enheten som oppgaven er tildelt") var tildeltEnhetsnr: String? = null,
    @Schema(description = "Oppgavens tema") var tema: String? = null,
    @Schema(description = "Oppgavens type") var oppgavetype: String? = null
) {
    constructor() : this("avvik ikke angitt")
    constructor(avvikType: AvvikType) : this(avvikType.name)
}

enum class AvvikType {
    ARKIVERE_JOURNALPOST,
    BESTILL_ORIGINAL,
    BESTILL_RESKANNING,
    BESTILL_SPLITTING,
    ENDRE_FAGOMRADE,
    SEND_TIL_FAGOMRADE, //DEPRECATED
    SEND_KOPI_TIL_FAGOMRADE,
    FEILFORE_SAK,
    INNG_TIL_UTG_DOKUMENT,
    OVERFOR_TIL_ANNEN_ENHET,
    SLETT_JOURNALPOST,
    TREKK_JOURNALPOST,
    REGISTRER_RETUR,
    MANGLER_ADRESSE,
    BESTILL_NY_DISTRIBUSJON

}
