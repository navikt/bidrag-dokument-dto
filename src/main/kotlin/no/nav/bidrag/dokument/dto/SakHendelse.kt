package no.nav.bidrag.dokument.dto

data class SakHendelse(
    val saksnummer: String,
    val hendelseType: SakHendelseType,
    val roller: List<SakRolle> = emptyList(),
    val sporingId: String,
)

data class SakRolle(
    val fødselsnummer: String,
    val rolleType: String
)
enum class SakHendelseType {
    ENDRING,
    OPPRETTELSE
}