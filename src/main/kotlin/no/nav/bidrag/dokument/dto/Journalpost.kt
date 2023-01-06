package no.nav.bidrag.dokument.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(description = "Metadata til en journalpost")
data class JournalpostDto(
    @Schema(description = "Avsenders navn (med eventuelt fornavn bak komma)", deprecated = true) var avsenderNavn: String? = null,
    @Schema(description = "Informasjon om avsender eller mottaker") var avsenderMottaker: AvsenderMottakerDto? = null,
    @Schema(description = "Dokumentene som følger journalposten") var dokumenter: List<DokumentDto> = emptyList(),
    @Schema(description = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
    @Schema(description = "Dato dokumentene på journalposten ble sendt til bruker.") var ekspedertDato: LocalDate? = null,
    @Schema(description = "Fagområdet for journalposten. BID for bidrag og FAR for farskap") var fagomrade: String? = null,
    @Schema(description = "Ident for hvem/hva dokumente(t/ne) gjelder") var gjelderIdent: String? = null,
    @Schema(description = "Aktøren for hvem/hva dokumente(t/ne) gjelder") var gjelderAktor: AktorDto? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var innhold: String? = null,
    @Schema(description = "Enhetsnummer hvor journalføring ble gjort") var journalforendeEnhet: String? = null,
    @Schema(description = "Saksbehandler som var journalfører") var journalfortAv: String? = null,
    @Schema(description = "Dato dokument ble journalført") var journalfortDato: LocalDate? = null,
    @Schema(description = "Identifikator av journalpost i midlertidig brevlager eller fra joark på formatet [BID|JOARK]-<journalpostId>") var journalpostId: String? = null,
    @Schema(description = "Kanalen som er kilden til at journalposten ble registrert", deprecated = true) var kilde: Kanal? = null,
    @Schema(description = "Kanalen journalposten ble mottatt i eller sendt ut på") var kanal: Kanal? = null,
    @Schema(description = "Dato for når dokument er mottat, dvs. dato for journalføring eller skanning") var mottattDato: LocalDate? = null,
    @Schema(description = "Inngående (I), utgående (U) journalpost; (X) internt notat") var dokumentType: String? = null,
    @Schema(description = "Journalpostens status, (A, D, J, M, O, R, S, T, U, KP, EJ, E)") var journalstatus: String? = null,
    @Schema(description = "Om journalposten er feilført på bidragssak") var feilfort: Boolean? = null,
    @Schema(description = "Brevkoden til en journalpost") var brevkode: KodeDto? = null,
    @Schema(description = "Informasjon om returdetaljer til journalpost") var returDetaljer: ReturDetaljer? = null,
    @Schema(description = "Joark journalpostid for bidrag journalpost som er arkivert i Joark") var joarkJournalpostId: String? = null,
    @Schema(description = "Adresse som utgående journalpost var distribuert til ved sentral print") var distribuertTilAdresse: DistribuerTilAdresse? = null,
    @Schema(description = "Informasjon om returdetaljer til journalpost") val sakstilknytninger: List<String> = emptyList(),
    @Schema(description = "Språket til dokumentet i Journalposten") val språk: String? = null,
    @Schema(description = "Saksbehandler som opprettet journalposten") val opprettetAvIdent: String? = null,
    )


@Schema(description = """
Avsender journalposten ble sendt fra hvis utgående.
Mottaker journalposten skal sendes til hvis inngående.""")
data class AvsenderMottakerDto(
    @Schema(description = "Avsenders/Mottakers navn (med eventuelt fornavn bak komma). Skal ikke oppgis hvis ident er en FNR") var navn: String? = null,
    @Schema(description = "Ident eller organisasjonsnummer") var ident: String? = null,
    @Schema(description = "Identtype") var type: AvsenderMottakerDtoIdType = AvsenderMottakerDtoIdType.FNR,
    @Schema(description = "Adresse til mottaker hvis dokumentet skal sendes/er sendt gjennom sentral print") val adresse: MottakerAdresseTo? = null
)

enum class AvsenderMottakerDtoIdType {
    FNR,
    ORGNR,
    HPRNR,
    UTL_ORG,
    UKJENT
}

data class MottakerAdresseTo(
    val adresselinje1: String,
    val adresselinje2: String? = null,
    val adresselinje3: String? = null,
    val bruksenhetsnummer: String? = null,
    @Schema(description = "Lankode må være i ISO 3166-1 alpha-2 format") val landkode: String? = null,
    @Schema(description = "Lankode må være i ISO 3166-1 alpha-3 format") val landkode3: String? = null,
    val postnummer: String? = null,
    val poststed: String? = null,
)

@Schema(description = "Metadata for retur detaljer")
data class ReturDetaljer(
    @Schema(description = "Dato for siste retur") var dato: LocalDate? = null,
    @Schema(description = "Totalt antall returer") var antall: Int? = null,
    @Schema(description = "Liste med logg av alle registrerte returer") var logg: List<ReturDetaljerLog>? = emptyList(),
)

@Schema(description = "Metadata for retur detaljer log")
data class ReturDetaljerLog(
    @Schema(description = "Dato for retur") var dato: LocalDate? = null,
    @Schema(description = "Beskrivelse av retur (eks. addresse forsøkt sendt)") var beskrivelse: String? = null,
    @Schema(description = "Returdetalje er låst for endring. Dette blir satt etter en ny distribusjon er bestilt") var locked: Boolean? = false,
)

@Schema(description = "Metadata om en aktør")
data class AktorDto(
    @Schema(description = "Identifaktor til aktøren") var ident: String = "",
    @Schema(description = "Hvilken identtype som skal brukes") var type: String? = null
) {
    fun hentIdentType(): IdentType? = if (type == null) null else try {
        IdentType.valueOf(type!!)
    } catch (e: Exception) {
        null
    }
}

@Schema(description = "Identtypene til en aktør")
enum class IdentType {
    @Schema(description = "AktoerId (nav sitt løpenummer)")
    AKTOERID,

    @Schema(description = "Fødselsnummer")
    FNR,

    @Schema(description = "Organisasjonsnummer ")
    ORGNR
}

@Schema(description = "Metadata for et dokument")
data class DokumentDto(
    @Schema(description = "Referansen til dokumentet i arkivsystemet") var dokumentreferanse: String? = null,
    @Schema(description = "Journalpost hvor dokumentet er arkivert. Dette brukes hvis dokumentet er arkivert i annen arkivsystem enn det som er sendt med i forespørsel.") val journalpostId: String? = null,
    @Schema(description = "Inngående (I), utgående (U) dokument, (X) internt notat", deprecated = true) var dokumentType: String? = null,
    @Schema(description = "Kort oppsummering av dokumentets innhold") var tittel: String? = null,
    @Schema(description = "Selve PDF dokumentet formatert som Base64") var dokument: String? = null,
    @Schema(description = "Typen dokument. Brevkoden sier noe om dokumentets innhold og oppbygning. Erstattes av dokumentmalId", deprecated = true) var brevkode: String? = null,
    @Schema(description = "Typen dokument. Dokumentmal sier noe om dokumentets innhold og oppbygning.") var dokumentmalId: String? = null,
    @Schema(description = "Dokumentets status. Benyttes hvis journalposten er av typen forsendelse") val status: DokumentStatusDto? = null,
    @Schema(description = "Arkivsystem hvor dokumentet er produsert og lagret") val arkivSystem: DokumentArkivSystemDto? = null,
    @Schema(description = "Metadata om dokumentet") val metadata: Map<String, String> = emptyMap(),
    )
{
    override fun toString(): String {
        return "(dokumentreferanse=${dokumentreferanse},journalpostId=$journalpostId, dokumentType=${dokumentType}, tittel=${tittel}, status=$status, arkivSystem=$arkivSystem " +
                "brevkode=${brevkode}, metadata=$metadata, dokument=${dokument?.subSequence(0, 20)}...)"
    }
}

@Schema(description = "Journalposten ble mottatt/sendt ut i kanal")
enum class Kanal {
    @Schema(description = "Ditt NAV (Innsending bidrag)")
    NAV_NO_BID,

    @Schema(description = "Skanning Bidrag")
    SKAN_BID,

    @Schema(description = "Ditt NAV")
    NAV_NO,

    @Schema(description = "Skanning Nets")
    SKAN_NETS,

    @Schema(description = "Lokal utskrift. Gjelder utgående journalposter som er printet og sendt ut manuelt")
    LOKAL_UTSKRIFT,

    @Schema(description = "Sentral utskrift. Brukes kun for utgående journalposter")
    SENTRAL_UTSKRIFT,

    @Schema(description = "Digital postkasse til innbyggere. Brevet er sendt via digital post til innbyggere. Brukes for utgående journalposter")
    SDP,

    @Schema(description = "Ingen distribusjon av journalpost. Mottaker har ingen gyldig adresse å sende til.")
    INGEN_DISTRIBUSJON,
}

@Schema(description = "Metadata for endring av en journalpost")
data class EndreJournalpostCommand(
    @Schema(description = "Identifikator av journalpost") var journalpostId: String? = null,
    @Schema(description = "Avsenders navn (med eventuelt fornavn bak komma)", deprecated = true) var avsenderNavn: String? = null,
    @Schema(description = "Avsender/Mottakers navn (med eventuelt fornavn bak komma)") var avsenderMottakerNavn: String? = null,
    @Schema(description = "Avsender/Mottakers id") var avsenderMottakerId: String? = null,
    @Schema(description = "Kort oppsummert av journalført innhold") var beskrivelse: String? = null,
    @Schema(description = "Dato for dokument i journalpost") var dokumentDato: LocalDate? = null,
    @Schema(description = "Fnr/dnr/bostnr eller orgnr for hvem/hva dokumentet gjelder") var gjelder: String? = null,
    @Schema(description = "Dato dokument ble journalført") var journaldato: LocalDate? = null,
    @Schema(description = "Saksnummer til bidragsaker som journalpost skal tilknyttes") var tilknyttSaker: List<String> = emptyList(),
    @Schema(description = "En liste over endringer i dokumenter") var endreDokumenter: List<EndreDokument> = emptyList(),
    @Schema(description = "Behandlingstema") var behandlingstema: String? = null,
    @Schema(description = "Endre fagområde") var fagomrade: String? = null,
    @Schema(description = "Type ident for gjelder: FNR, ORGNR, AKTOERID") var gjelderType: String? = null,
    @Schema(description = "Tittel på journalposten") var tittel: String? = null,
    @Schema(description = "Skal journalposten journalføres aka. registreres") var skalJournalfores: Boolean = false,
    @Schema(description = "Liste med retur detaljer som skal endres") var endreReturDetaljer: List<EndreReturDetaljer>? = null
    ) {
    @Suppress("unused")
    fun manglerGjelder() = gjelder == null
}

@Schema(description = "Metadata for endring av et retur detalj")
data class EndreReturDetaljer (
    @Schema(description = "Dato på retur detaljer som skal endres") var originalDato: LocalDate? = null,
    @Schema(description = "Ny dato på retur detaljer") var nyDato: LocalDate? = null,
    @Schema(description = "Beskrivelse av retur (eks. addresse forsøkt sendt)") var beskrivelse: String,
)

@Schema(description = "Metadata for endring av et dokument")
data class EndreDokument(
    @Schema(description = "Brevkoden til dokumentet") val brevkode: String? = null,
    @Schema(description = "Identifikator av dokument informasjon", deprecated = true) val dokId: String? = null,
    @Schema(description = "Identifikator til dokumentet") val dokumentreferanse: String? = null,
    @Schema(description = "Tittel på dokumentet") val tittel: String? = null
)

@Schema(description = "Metadata for kode vs dekode i et kodeobjekt")
data class KodeDto(
    @Schema(description = "Koden") var kode: String? = null,
    @Schema(description = "Dekode (kodebeskrivelse)") var dekode: String? = null,
    @Schema(description = "Om kodeobjektet inneholder en gyldig verdi") var erGyldig: Boolean = true
)

@Schema(description = "Metadata til en respons etter journalpost med tilhørende data")
data class JournalpostResponse(
    @Schema(description = "journalposten som er etterspurt") var journalpost: JournalpostDto? = null,
    @Schema(description = "alle saker som journalposten er tilknyttet") var sakstilknytninger: List<String> = emptyList()
)


@Schema(description = "Metadata for opprettelse av journalpost")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class OpprettJournalpostRequest(
    @Schema(description = "Om journalposten skal journalføres etter opprettelse. Journalføring betyr at journalpost låses for framtidige endringer") val skalFerdigstilles: Boolean = false,
    @Schema(description = "Tittel på journalposten (Tittel settes til hoveddokumentes tittel for Joark journalposter)", deprecated = true) val tittel: String? = null,
    @Schema(description = "Bruker som journalposten gjelder") val gjelder: AktorDto? = null,
    @Schema(description = "Ident til brukeren som journalposten gjelder") val gjelderIdent: String? = null,
    val avsenderMottaker: AvsenderMottakerDto? = null,
    @Schema(description = """
    Dokumenter som skal knyttes til journalpost. 
    En journalpost må minst ha et dokument. 
    Det første dokument i meldingen blir tilknyttet som hoveddokument på journalposten.""", required = true)
    val dokumenter: List<OpprettDokumentDto> = emptyList(),
    @Schema(description = "Saksnummer til bidragsaker som journalpost skal tilknyttes") val tilknyttSaker: List<String> = emptyList(),
    @Schema(description = "Behandlingstema") val behandlingstema: String? = null,
    @Schema(description = "Dato journalposten mottatt. Kan settes for inngående journalposter. Settes til i dag som default hvis ikke satt") val datoMottatt: LocalDateTime? = null,
    @Schema(description = "Type kanal som benyttes ved mottak/utsending av journalpost", defaultValue = "DIGITALT") val kanal: MottakUtsendingKanal? = null,
    @Schema(description = "Tema (Gyldige verdier er FAR og BID). Hvis det ikke settes opprettes journalpost med tema BID", defaultValue = "BID") val tema: String? = null,
    @Schema(description = "Journalposttype, dette kan enten være Inngående, Utgående eller Notat", required = true) val journalposttype: JournalpostType? = null,
    @Schema(description = "Referanse for journalpost. Hvis journalpost med samme referanse finnes vil tjenesten gå videre uten å opprette journalpost. Kan brukes for å lage løsninger idempotent") val referanseId: String? = null,
    @Schema(description = "NAV-enheten som oppretter journalposten", deprecated = true) val journalfoerendeEnhet: String? = null,
    @Schema(description = "NAV-enheten som oppretter journalposten") val journalførendeEnhet: String? = null,
    @Schema(description = "Ident til saksbehandler som oppretter journalpost. Dette vil prioriteres over ident som tilhører tokenet til kallet.") val saksbehandlerIdent: String? = null,
)

@Schema(description = "Metadata til en respons etter journalpost ble opprettet")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class OpprettJournalpostResponse(
    @Schema(description = "Journalpostid på journalpost som ble opprettet") val journalpostId: String? = null,
    @Schema(description = "Liste med dokumenter som er knyttet til journalposten") val dokumenter: List<OpprettDokumentDto> = emptyList(),
)
@Schema(description = "Metadata for dokument som skal knyttes til journalpost")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class OpprettDokumentDto(
    @Schema(description = "Dokumentets tittel") val tittel: String = "",
    @Schema(description = "Typen dokument. Brevkoden sier noe om dokumentets innhold og oppbygning.") val brevkode: String? = null,
    @Schema(description = "Referansen til dokumentet hvis det er lagret i et annet arkivsystem") val dokumentreferanse: String? = null,
    @Schema(description = "Selve PDF dokumentet formatert som Base64", deprecated = true) val dokument: String? = null,
    @Schema(description = "Selve PDF dokumentet formatert som Base64") val fysiskDokument: ByteArray? = null,
) {
    override fun toString(): String {
        return "(tittel=${tittel}, brevkode=${brevkode}, dokumentreferanse=${dokumentreferanse}, " +
                "fysiskDokument(lengde)=${fysiskDokument?.size ?: 0}, dokument=${dokument?.subSequence(0, 20)}"
    }
}
enum class JournalpostType {
    INNGÅENDE,
    @Schema(description = "Bruk UTGÅENDE istedenfor", deprecated = true) UTGAAENDE,
    UTGÅENDE,
    NOTAT
}
object Journalstatus {
    const val MOTTATT = "M"
    const val JOURNALFORT = "J"
    const val EKSPEDERT = "E"
    const val AVBRUTT = "A"
    const val KLAR_TIL_PRINT = "KP"
    const val RETUR = "RE"
    const val FERDIGSTILT = "FS"
    const val FEILREGISTRERT = "F"
    const val RESERVERT = "R"
    const val UTGAR = "U"
}

object Fagomrade {
    const val BIDRAG = "BID"
    const val FARSKAP = "FAR"
}

@Schema(description = """
    Mottak/Utsendingskanal som settes ved opprettelse av journalpost
    
    DIGITAL - Skal bare settes for inngående journalpost. Oversettes til NAV_NO. Dette er default for inngående
    
    SKANNING_BIDRAG - Skal settes hvis inngående journalpost er mottatt via Bidrag skanning
    
    LOKAL_UTSKRIFT - Skal settes hvis utgående journalpost er sendt via lokal utskrift. Kanal for utgående journalposter blir ellers satt av dokumentdistribusjons løsningen.
""")
enum class MottakUtsendingKanal {
    DIGITALT,
    SKANNING_BIDRAG,
    LOKAL_UTSKRIFT
}