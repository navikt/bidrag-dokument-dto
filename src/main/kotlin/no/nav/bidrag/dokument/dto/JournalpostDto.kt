package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.lang.IllegalStateException
import java.time.LocalDate

@ApiModel(value = "Metadata for en journalpost, no.nav.bidrag.dokument::bidrag-dokument-dto")
data class JournalpostDto(
        @ApiModelProperty(value = "Avsenders navn (med eventuelt fornavn bak komma)") var avsenderNavn: String? = null,
        @ApiModelProperty(value = "Dokumentene som følger journalposten") var dokumenter: List<DokumentDto> = emptyList(),
        @ApiModelProperty(value = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
        @ApiModelProperty(value = "Fagområdet for journalposten. BID for bidrag og FAR for farskap") var fagomrade: String? = null,
        @ApiModelProperty(value = "Aktøren for hvem/hva dokumente(t/ne) gjelder") var gjelderAktor: AktorDto? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var innhold: String? = null,
        @ApiModelProperty(value = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
        @ApiModelProperty(value = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
        @ApiModelProperty(value = "Dato dokument ble journalført") var journalfortDato: LocalDate? = null,
        @ApiModelProperty(value = "Identifikator av journalpost i midlertidig brevlager eller fra joark på formatet [BID|JOARK]-<journalpostId>") var journalpostId: String? = null,
        @ApiModelProperty(value = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
        @ApiModelProperty(value = "Saksnummer på bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Liste over saker hvor journalpostens sin aktør er representert (når det er en person)") var bidragssaker: List<BidragSakDto> = emptyList(),
        @ApiModelProperty(value = "Inngående (I), utgående (U) journalpost; (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Journalpostens status, (A, D, J, M, O, R, T, U)") var journalstatus: String? = null
)

@ApiModel(value = "Dokument metadata")
data class DokumentDto(
        @ApiModelProperty(value = "Referanse som brukes når dokument er i midlertidig-brevlager") var dokumentreferanse: String? = null,
        @ApiModelProperty(value = "Inngående (I), utgående (U) dokument, (X) internt notat") var dokumentType: String? = null,
        @ApiModelProperty(value = "Kort oppsummert av journalført innhold") var tittel: String? = null
)

@ApiModel(value = "Metadata om en bidragssak")
data class BidragSakDto(
        @ApiModelProperty(value = "Eierfogd for bidragssaken") var eierfogd: String? = null,
        @ApiModelProperty(value = "Saksnummeret til bidragssaken") var saksnummer: String? = null,
        @ApiModelProperty(value = "Saksstatus til bidragssaken") var saksstatus: String? = null,
        @ApiModelProperty(value = "Kategorikode: 'N' eller 'U'") var kategori: String? = null,
        @ApiModelProperty(value = "Om saken omhandler paragraf 19") var erParagraf19: Boolean = false,
        @ApiModelProperty(value = "Rollene som saken inneholder") var roller: List<RolleDto> = emptyList()
)

@ApiModel(value = "Metadata om en rolle i en bidraggsak (når rollen er en person)")
data class RolleDto(
        @ApiModelProperty(value = "Fødselsnummer til en person i en bidragssak") var foedselsnummer: String? = null,
        @ApiModelProperty(value = "Rolletypen til en person i en bidragssak, f.eks: BM eller BP (bidragsmottaker eller bidragspliktig)") var rolleType: String? = null
)

@ApiModel(value = "Metadata om en aktør")
open class AktorDto(
        @ApiModelProperty(value = "Identifaktor til aktøren") var ident: String,
        @ApiModelProperty(value = "Identtypen til identen (bnr, NorskIdent eller orgnr)") var identType: String,
        @ApiModelProperty(value = "Aktørtype (person eller organisasjon)") val aktorType: String
) {
    constructor() : this ("", "ukjent", "ukjent")

    fun fetchIdentType(): String {
        if (identType.isNotEmpty()) return identType
        if (aktorType == "person") return personIdentType(ident)
        if (aktorType == "organisasjon") return organisasjonIdentType(ident)

        throw IllegalStateException("Ukjent aktørstype: $this")
    }

    private fun personIdentType(ident: String): String {
        if (erIkkeIdentMedElleveSiffer(ident)) throwUkjentIdentType()
        return if ((ident.subSequence(2, 4) as String).toInt() > 20) "bnr" else "NorskIdent"
    }

    private fun erIkkeIdentMedElleveSiffer(ident: String) = ident.length != 11 || !ident.matches("[0-9]*".toRegex())

    private fun organisasjonIdentType(ident: String): String {
        if (ident.length != 9 || !ident.matches("[0-9]*".toRegex())) throwUkjentIdentType()
        return "orgnr"
    }

    private fun throwUkjentIdentType(): Nothing {
        throw IllegalStateException("Ukjent ident ($ident) for $aktorType")
    }

    fun erPerson(): Boolean {
        return this is PersonDto || erIdentMedElleveSiffer(ident)
    }

    private fun erIdentMedElleveSiffer(ident: String): Boolean {
        return !erIkkeIdentMedElleveSiffer(ident)
    }
}

@ApiModel(value = "Metadata om en person")
data class PersonDto(
        @ApiModelProperty(value = "Identen til personen") private val personIdent: String,
        @ApiModelProperty(value = "Navn til person på formatet <etternavn, fornavn>") var navn: String? = null,
        @ApiModelProperty(value = "Dødsdato til død person") var doedsdato: LocalDate? = null,
        @ApiModelProperty(value = "Diskresjonskode (personvern)") var diskresjonskode: String? = null
) : AktorDto(personIdent, "", "person") {
    constructor() : this("", null, null, null)
    constructor(ident: String) : this(ident, null, null, null)
}

@ApiModel(value = "Metadata om en aktør")
data class OrganisasjonDto(
        @ApiModelProperty(value = "Identifaktor til organisasjonen") private val orgIdent: String
) : AktorDto(orgIdent, "", "organisasjon") {
    constructor() : this("")
}
