package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.Optional

@ApiModel(value = "En avvikshendelse som kan utføres på en journalpost")
data class Avvikshendelse(
        @ApiModelProperty(value = "Type avvik") var avvikType: String,
        @ApiModelProperty(value = "Enhetsnummer for gjeldense avvik, eks: sendes til i BESTILL_ORIGINAL") var enhetsnummer: String? = null,
        @ApiModelProperty(value = "Manuell beskrivelse av avvik") var beskrivelse: String? = null,
        @ApiModelProperty(value = "Eventuelle detaljer som skal følge avviket") var detaljer: Map<String, String> = HashMap(),
        @ApiModelProperty(value = "Saksnummer til sak når journalpost er journalført") var saksnummer: String?
) {
    constructor()
            : this(avvikType = "avvik ikke angitt", enhetsnummer = null, beskrivelse = null, saksnummer = null)

    constructor(avvikType: String, enhetsnummer: String?)
            : this(avvikType, enhetsnummer, beskrivelse = null, saksnummer = null)

    constructor(avvikType: String, enhetsnummer: String?, detaljer: Map<String, String>, saksnummer: String?)
            : this(avvikType, enhetsnummer, null, detaljer, saksnummer)

    constructor(avvikType: String, enhetsnummer: String?, saksnummer: String?)
            : this(avvikType, enhetsnummer, beskrivelse = null, saksnummer = saksnummer)

    fun hent(): Optional<AvvikType> {
        try {
            return Optional.of(AvvikType.valueOf(avvikType))
        } catch (exception: Exception) {
            return Optional.empty()
        }
    }
}

@ApiModel(value = "Responsen til en avvikshendelse med oppgave")
data class OpprettAvvikshendelseResponse(
        @ApiModelProperty(value = "Type avvik") var avvikType: String,
        @ApiModelProperty(value = "Oppgave id for oppgaven som ble opprettet på bakgrunn av avviket") var oppgaveId: Long? = null,
        @ApiModelProperty(value = "Enhetsnummer til enheten som oppgaven er tildelt") var tildeltEnhetsnr: String? = null,
        @ApiModelProperty(value = "Oppgavens tema") var tema: String? = null,
        @ApiModelProperty(value = "Oppgavens type") var oppgavetype: String? = null
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
    INNG_TIL_UTG_DOKUMENT,
    FEILFORE_SAK,
    SLETT_JOURNALPOST,
    TREKK_JOURNALPOST
}
