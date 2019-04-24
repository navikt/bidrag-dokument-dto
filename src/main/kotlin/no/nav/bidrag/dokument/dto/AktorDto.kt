package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.Optional

@ApiModel(value = "Metadata om en aktør")
open class AktorDto(
        @ApiModelProperty(value = "Identifaktor til aktøren") var ident: String,
        @ApiModelProperty(value = "Identtypen til identen (bnr, NorskIdent eller orgnr)") var identType: String,
        @ApiModelProperty(value = "Aktørtype (person eller organisasjon)") val aktorType: String
) {
    constructor() : this("", "ukjent", "ukjent")
    constructor(ident: String, type: String) : this(ident, type, "ukjent")

    fun fetchIdentType(): String {
        if (identType.isNotEmpty()) return identType
        if (aktorType == "person") return personIdentType()
        if (aktorType == "organisasjon") return organisasjonIdentType(ident)

        throw IllegalStateException("Ukjent aktørstype: $this")
    }

    fun fetchPersonIdentType(): Optional<String> {
        if (aktorType == "organisasjon") return Optional.empty()
        if (erIkkeIdentMedSiffer()) return Optional.empty()
        if (erIkkeLengdenPaIdentenElleveTegn()) return Optional.of("AKTOERID")

        return Optional.of(personIdentTypeWithoutException())
    }

    private fun personIdentTypeWithoutException(): String {
        return if (ident.substring(2, 4).toInt() > 20) "bnr" else "NorskIdent"
    }

    private fun erIkkeLengdenPaIdentenElleveTegn(): Boolean = ident.length != 11

    private fun erIkkeIdentMedSiffer(): Boolean = !ident.matches("[0-9]*".toRegex())

    private fun personIdentType(): String {
        if (erIkkeIdentMedElleveSiffer()) throwUkjentIdentType()
        return personIdentTypeWithoutException()
    }

    private fun erIkkeIdentMedElleveSiffer() = ident.length != 11 || !ident.matches("[0-9]*".toRegex())

    private fun organisasjonIdentType(ident: String): String {
        if (ident.length != 9 || !ident.matches("[0-9]*".toRegex())) throwUkjentIdentType()
        return "orgnr"
    }

    private fun throwUkjentIdentType(): Nothing {
        throw IllegalStateException("Ukjent ident ($ident) for $aktorType")
    }

    fun erPerson(): Boolean {
        if (this is PersonDto) return true
        if (erIdentMedElleveSiffer() && aktorType != "organisasjon") return true
        if (erIdentMedSiffer() && aktorType == "person") return true

        return false
    }

    private fun erIdentMedElleveSiffer(): Boolean = erIdentMedSiffer() && ident.length == 11

    private fun erIdentMedSiffer(): Boolean = !erIkkeIdentMedSiffer()
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

@ApiModel(value = "Metadata om en organisasjon")
data class OrganisasjonDto(
        @ApiModelProperty(value = "Identifaktor til organisasjonen") private val orgIdent: String
) : AktorDto(orgIdent, "", "organisasjon") {
    constructor() : this("")
}
