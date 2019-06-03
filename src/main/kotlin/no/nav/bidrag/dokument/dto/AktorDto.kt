package no.nav.bidrag.dokument.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.Optional

@ApiModel(value = "Metadata om en aktør")
data class AktorDto(
        @ApiModelProperty(value = "Identifaktor til aktøren") var ident: String
) {
    constructor() : this("")

    fun fetchIdentType(): String {
        if (erPerson()) return personIdentType()
        if (erIdentMedSiffer()) return "orgnr"

        return "ukjent"
    }

    fun fetchPersonIdentType(): Optional<String> {
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

    private fun throwUkjentIdentType(): Nothing {
        throw IllegalStateException("Ukjent ident ($ident): utredet identtype er ${fetchIdentType()}.")
    }

    fun erPerson(): Boolean {
        if (erIdentMedElleveSiffer() || (erIdentMedSiffer() && ident.length != 9)) return true

        return false
    }

    fun erIkkePerson(): Boolean {
        return !erPerson()
    }

    private fun erIdentMedElleveSiffer(): Boolean = erIdentMedSiffer() && ident.length == 11

    private fun erIdentMedSiffer(): Boolean = !erIkkeIdentMedSiffer()
}
