package no.nav.bidrag.dokument.dto

data class SakHendelse(
    val saksnummer: String,
    val hendelseType: SakHendelseType,
    val roller: List<SakRolle> = emptyList(),
    val sporingId: String,
)

data class SakRolle(
    val ident: String? = null,
    val rolleType: String,
    val samhandlerId: String? = null,
    val reelmottaker: String? = null,
    val erUkjent: Boolean = false
)
enum class SakHendelseType {
    ENDRING,
    OPPRETTELSE
}