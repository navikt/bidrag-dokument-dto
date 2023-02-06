# bidrag-dokument-dto

![](https://github.com/navikt/bidrag-dokument-dto/workflows/maven%20deploy/badge.svg)

Artifakt inneholder dto'er for felles kommunikasjon i bidrag-dokument

## release endringer

| versjon  | endringstype      | beskrivelse                                                                                                                                                           |
|----------|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.6.15   | Endret            | `JournalpostId` lagt til metode for å hente journalpostid med prefiks                                                                                                 |
| 1.6.14   | Opprettet         | `JournalpostId` klasse for felles behandling journalpost id                                                                                                           |
| 1.6.14   | Endret            | `DokumentType` og `Journalstatus` kode verdier                                                                                                                        |
| 1.6.13   | Endret            | `ÅpneDokumentMetadata` endret navn til `DokumentMetadata`                                                                                                             |
| 1.6.12   | Endret            | `DistribuerJournalpostRequest` lagt til parameter `lokalUtskrift`                                                                                                     |
| 1.6.11   | Endret            | `EndreDokument` endret dokId typen fra Long til String                                                                                                                |
| 1.6.10   | Endret            | `ÅpneDokumentMetadata` legg til arkivsystem                                                                                                                           |
| 1.6.9    | Endret            | `ÅpneDokumentMetadata` legg til journalpostid og dokumentreferanse                                                                                                    |
| 1.6.8    | Opprettet         | `ÅpneDokumentMetadata` for informasjon om hvordan dokument skal åpnes                                                                                                 |
| 1.6.7    | Endret            | Oppdater `DokumentHendelse` og Dokument dto                                                                                                                           |
| 1.6.6    | Endret            | Oppdater `JournalpostDto` lagt til opprettetAvIdent                                                                                                                   |
| 1.6.5    | Endret            | Oppdater `JournalpostDto` lagt til mottaker landkode3 og språk                                                                                                        |
| 1.6.4    | Endret            | Oppdater `JournalpostDto.DokumentDto` lagt til parameter arkivsystem og metadata                                                                                      |
| 1.6.3    | Endret            | Oppdater `JournalpostDto.DokumentDto` lagt til parameter status                                                                                                       |
| 1.6.2    | Endret            | Oppdater `JournalpostDto` med støtte for forsendelse parametere                                                                                                       |
| 1.6.1    | Endret            | Oppdater `DokumentHendelse` data objekt                                                                                                                               |
| 1.6.0    | Opprettet         | `DokumentHendelse` data objekt                                                                                                                                        |
| 1.5.18   | Endret            | `OpprettDokumentDto` lagt til toString metode for å hindre logging av dokument                                                                                        |
| 1.5.17   | Endret            | `OpprettJournalpostRequest` fjern default verdier                                                                                                                     |
| 1.5.16   | Endret            | `OpprettJournalpostRequest` endret paramter `skalJournalføres` til `skalFerdigstilles`                                                                                |
| 1.5.16   | Endret            | `JournalpostHendelse` lagt til parameter `behandlingstema`                                                                                                            |
| 1.5.15   | Endret            | `OpprettJournalpostRequest` refaktorer                                                                                                                                |
| 1.5.14   | Endret            | `OpprettJournalpostRequest` lagt til `saksbehandlerIdent`                                                                                                             |
| 1.5.13   | Endret            | `OpprettJournalpostRequest` og  `OpprettJournalpostResponse`                                                                                                          |
| 1.5.12   | Endret            | `JournalpostHendelse` add property `hendelseType`                                                                                                                     |
| 1.5.11   | Endret            | `JournalpostHendelse` add property `journalposttype`                                                                                                                  |
| 1.5.10   | Endret            | `JournalpostHendelse` oppdatert hentSaksbehandlerInfo metode                                                                                                          |
| 1.5.9    | Endret            | `JournalpostHendelse` oppdatert hentSaksbehandlerInfo metode                                                                                                          |
| 1.5.8    | Endret            | `JournalpostHendelse` fjernet ubrukte metoder                                                                                                                         |
| 1.5.7    | Endret            | `JournalpostHendelse` legg til `sakstilknytninger` og `tittel`                                                                                                        |
| 1.5.7    | Fiks              | `DokumentDto` legg til toString metode                                                                                                                                |
| 1.5.6    | Opprettet         | `OpprettJournalpostRequest` og `OpprettJournalpostResponse` objekter                                                                                                  |
| 1.5.6    | Lagt til          | Ny `AvvikType`: KOPIER_FRA_ANNEN_FAGOMRADE                                                                                                                            |
| 1.5.5    | Lagt til          | Add `fnr` to `JournalpostHendelse`                                                                                                                                    |
| 1.5.4    | Lagt til          | Remove parameter `locked` på `ReturDetaljer`                                                                                                                          |
| 1.5.4    | Lagt til          | Ny parameter `locked` på `ReturDetaljerLog`                                                                                                                           |
| 1.5.3    | Lagt til          | Ny parameter `locked` på `ReturDetaljer`                                                                                                                              |
| 1.5.2    | Lagt til          | Ny kanal `INGEN_DISTRIBUSJON`                                                                                                                                         |
| 1.5.1    | Lagt til          | Ny optional parameter `adresse` i Avvikshendelse                                                                                                                      |
| 1.5.1    | Lagt til          | Ny `AvvikType`: BESTILL_NY_DISTRIBUSJON, MANGLER_ADRESSE, SEND_KOPI_TIL_FAGOMRADE                                                                                     |
| 1.5.0    | Lagt til          | `JournalpostDto`: add avsenderMottaker property                                                                                                                       |
| 1.4.5    | Lagt til          | Ny `AvvikType`: SEND_TIL_FAGOMRADE                                                                                                                                    |
| 1.4.4    | Lagt til          | `JournalpostDto`: add ekspedertDato property                                                                                                                          |
| 1.4.3    | Endret            | `DistribuerTilAdresse`: land property to optional                                                                                                                     |
| 1.4.2    | Endret            | `DistribuerTilAdresse`: remove adressetype property                                                                                                                   |
| 1.4.2    | Lagt til          | `JournalpostDto`: add distribuertTilAdresse property                                                                                                                  |
| 1.4.1    | Lagt til          | `JournalpostDto`: add joarkJournalpostId property                                                                                                                     |
| 1.4.0    | Lagt til          | `Kanal`: add `SENTRAL_UTSKRIFT`, `SDP` and `LOKAL_UTSKRIFT`                                                                                                           |
| 1.4.0    | Lagt til          | `JournalpostDto`: add kanal property                                                                                                                                  |
| 1.4.0    | Lagt til          | `DistribuerJournalpostRequest` og `DistribuerJournalpostResponse`                                                                                                     |
| 1.4.0    | Slettet           | `AvvikType`: OPPDATER_DISTRIBUSJONSINFO                                                                                                                               |
| 1.3.0    | Lagt til          | Ny `AvvikType`: OPPDATER_DISTRIBUSJONSINFO                                                                                                                            |
| 1.3.0    | Slettet           | `JournalpostDto`: remove joarkJournalpostId property                                                                                                                  |
| 1.2.0    | Lagt til          | `JournalpostDto`: add joarkJournalpostId property                                                                                                                     |
| 1.1.0    | Endret            | Ny `AvvikType`: REGISTRER_RETUR                                                                                                                                       |
| 1.1.0    | Lagt til          | `EndreJournalpostCommand`: add endreReturDetaljer property                                                                                                            |
| 1.1.0    | Lagt til          | `JournalpostDto`: add returDetaljer property                                                                                                                          |
| 1.0.1.1  | Endret            | `JournalpostHendelse`: secondary constructor                                                                                                                          |
| 1.0.1    | Endret            | `JournalpostHendelse.sporing`: lagt til sporing av enhetsnummer                                                                                                       |
| 1.0.0    | Endret            | `JournalpostHendelse`: slettet alternativ konstruktør som bruker minimalt med data                                                                                    |
| 1.0.0    | Slettet           | `OpprettAvvikshendelseResponse`: deprecated, since 0.16.0                                                                                                             |
| 1.0.0    | Slettet           | `RegistrereJournalpostCommand`: deprecated, since 0.10.0                                                                                                              |
| 0.22.2   | Endret            | springdoc-openapi-ui dependency and spring-boot test dependencies                                                                                                     |
| 0.22.1   | Endret            | `JournalpostHendelse`: Ny alternativ konstruktør                                                                                                                      |
| 0.22.0   | Opprettet         | `JournalpostHendelse`: Hendelse for endret journalpost                                                                                                                |
| 0.21.1   | Endret            | `AktorDto`: type er et nullable felt                                                                                                                                  |
| 0.21.0   | Opprettet         | `IdentType`: enum med verdier: [AKTOERID, FNR, ORGNR]                                                                                                                 |
| 0.21.0   | Endret            | `AktorDto`: ny property `type` (streng som kan hentes som enum)                                                                                                       |
| 0.21.0   | Endret            | bumped kotlin version -> 1.5.31                                                                                                                                       |
| 0.20.0   | Opprettet         | `JournalpostBeskrivelseException`                                                                                                                                     |
| 0.20.0   | Opprettet         | `JournalpostHendelseException`                                                                                                                                        |
| 0.20.0   | Opprettet         | `OppgaveException`                                                                                                                                                    |
| 0.20.0   | Opprettet         | `TokenException`                                                                                                                                                      |
| 0.19.1   | Endret            | bumped kotlin version -> 1.5.30                                                                                                                                       |
| 0.19.0   | Opprettet         | `JournalpostIkkeFunnetException`                                                                                                                                      |
| 0.19.0   | Opprettet         | `OppgaveIkkeOpprettetException`                                                                                                                                       |
| 0.18.0   | dokId, Int->Long  | `EndreDokument.dokId`: type fra Int -> Long                                                                                                                           |
| 0.17.0   | Opprettet         | `Kanal`: enum for en journalposts kilde                                                                                                                               |
| 0.17.0   | Lagt til/Endret   | `JournalpostDto.kilde` er lagt til, samt swagger avhengigheter er nå springdoc-openapi-ui                                                                             |
| 0.16.0   | Lagt til          | `OpprettAvvikshendelseResponse` deprecated, `BehandleAvvikshendelseResponse` opprettet                                                                                |
| 0.15.6   | Endret            | bumped kotlin version 1.4.32 -> 1.5.0                                                                                                                                 |
| 0.15.5   | Endret            | bumped kotlin version 1.4.31 -> 1.4.32                                                                                                                                |
| 0.15.4   | Endret            | bumped kotlin version 1.4.30 -> 1.4.31                                                                                                                                |
| 0.15.3   | Endret            | `Avvikshendelse`: henter AvvikType uten å bruke exception handling...                                                                                                 |
| 0.15.2   | Endret            | Ny `AvvikType`: OVERFOR_TIL_ANNEN_ENHET                                                                                                                               |
| 0.15.1   | Endret            | Avhengigheter til kotlin og spring-boot                                                                                                                               |
| 0.15.0   | Endret            | `Avvikshendelse` bruker enhetsnummer som et innhold i detaljer-map                                                                                                    |
| 0.14.1   | Endret            | Springfox avhengigheter er nå compile time (og versjon 3.0.0).                                                                                                        |
| 0.14.0   | Endret/Opprettet  | Ny `AvvikType` TREKK_JOURNALPOST, for sletting av mottakregistrert journalpost.                                                                                       |
| 0.13.0   | Slettet           | `EndreJournalpostCommand`: Slettet felt journalforendeEnhet                                                                                                           |
| 0.12.0   | Endring/ omstrukt | Tilbakestille `OpprettAvvikshendelseResponse` til bare én responstype                                                                                                 |
| 0.11.0   | Endring/ omstrukt | Ny `AvvikType` ARKIVERE_JOURNALPOST. Omstrukturere `OpprettAvvikshendelseRespone` for å åpne for forskjellige responstyper. `Avvikshendelse` lagt til ny konstruktør. |
| 0.10.1   | Endring           | `Avvikshendelse` inneholder saksnummer når et avvik blir opprettet for en sak                                                                                         |
| 0.10.0.1 | Endring           | `RegistrereJournalpostCommand` er lagt inn, men vil bli slettet etter fullstendig oppgradering                                                                        |
| 0.10.0   | Endring           | `EndreJournalpostCommand`: Smeltes sammen med `RegistrereJournalpostCommand`                                                                                          |
| 0.10.0   | Slettet           | `RegistrereJournalpostCommand`: Smeltes sammen med `EndreJournalpostCommand`                                                                                          |
| 0.10.0   | Slettet           | `EndreSaksnummer`: Funksjon videreføres med `EndreJournalpostCommand.tilknyttSaker`                                                                                   |
| 0.9.0    | Opprettet         | `JournalpostResponse`: `JournalpostDto` med tilhørende data                                                                                                           |
| 0.8.2    | Endring           | `io.swagger.annotations.ApiModel`: Synkronisert beskrivelser                                                                                                          |
| 0.8.1    | Endring           | `RegistrereJournalpostCommand`: `skalJournalfores` har default verdi: true                                                                                            |
| 0.8.0    | Opprettet         | `RegistrereJournalpostCommand`: registrere (journalføre) mottaksregistrert journalpost                                                                                |
| 0.7.0    | Slettet           | `NyJournalpostCommand`: Journalposter blir registrert av annet system                                                                                                 |
| 0.6.0    | -- ingen --       | Overgang til bruk av github som mavenrepo                                                                                                                             |
| 0.5.0    | Slettet           | `EndretJournalpostResponse`: Vi skal ikke returnere noen response når en journalpost oppdateres                                                                       |
| 0.4.2    | Opprettet         | `OpprettDokument`: nye felter for oppretting av et dokument på en journalpost med journalpostapi v1                                                                   |
| 0.4.2    | Endring           | `NyJournalpostCommand`: nye felter for oppretting av journalpost med journalpostapi v1                                                                                |
| 0.4.1    | Opprettet         | `EndretJournalpostResponse`                                                                                                                                           |
| 0.4.0    | Slettet           | `RolleDto`                                                                                                                                                            |
| 0.4.0    | Slettet           | `EndreJournalpostCommand.harIkkeJournalpostIdSammeVerdi(String)`                                                                                                      |
| 0.4.0    | Endring/Opprettet | `EndreJournalpostCommand`: nye felter som bare er aktuelle ifm. oppdateringer i joark                                                                                 |
| 0.4.0    | Endring           | `NyJournalpostCommand`, endret fra gammelt navn: `NyJournalpostCommandDto`                                                                                            |
| 0.4.0    | Endring           | `EndreJournalpostCommand`, endret fra gammelt navn: `EndreJournalpostCommandDto`                                                                                      |
| 0.4.0    | Endring           | `EndreSaksnummer`, endret fra gammelt navn: `EndreSaksnummerDto`                                                                                                      |
| 0.3.1    | Endring           | `AvvikType`: SLETT_JOURNALPOST                                                                                                                                        |
| 0.3.0    | Endring/Opprettet | `EndreJournalpostCommandDto`, ny metode som kvalitetsikrer journalpostId                                                                                              |
| 0.2.27   | Endring           | `AvvikType`: FEILFORE -> FEILFORE_SAK                                                                                                                                 |
| 0.2.26   | Endring/Opprettet | `AvvikType`, ny enumeration: FEILFORE                                                                                                                                 |
| 0.2.25   | Endring/Opprettet | `AvvikType`, ny enumeration: INNG_TIL_UTG_DOKUMENT                                                                                                                    |
| 0.2.24   | Endring/Opprettet | `AvvikType`, ny enumeration: ENDRE_FAGOMRADE                                                                                                                          |
| 0.2.23   | Endring/Opprettet | `JournalpostDto`, nytt felt: `brevkode`                                                                                                                               |
| 0.2.23   | Opprettet         | `KodeDto`, kode / dekode                                                                                                                                              |
| 0.2.22   | Endring/Opprettet | `Avvikshendelse`, konstruktør med argumenter utenom beskrivelse                                                                                                       |
| 0.2.21   | Endring/Opprettet | `Avvikshendelse`, beskrivelse: input fra saksbehandler                                                                                                                |
| 0.2.20   | Endring/Opprettet | `AvvikType`, ny enumeration: BESTILL_SPLITTING                                                                                                                        |
| 0.2.17   | Endring           | `Avvikshendelse`, Swagger dokumentasjon og ingen not nullable felter                                                                                                  |
| 0.2.17   | Endring           | `Avvikshendelse`, Swagger dokumentasjon og ingen not nullable felter                                                                                                  |
| 0.2.12   | Endring           | `AvvikType`, `BESTILL_ORGINAL`-> `BESTILL_ORIGINAL`                                                                                                                   |
| 0.2.11   | Endring/Opprettet | `Avvikshendelse.enhetsummer`, brukes foreløpig til beriking av tknr og navn ved `BestillOriginal`                                                                     |
| 0.2.10   | Endring/Opprettet | `AvvikType.BESTILL_RESKANNING`                                                                                                                                        |
| 0.2.9    | Endring/Opprettet | gjenopprettet konstruktør `OpprettAvvikshendelse`                                                                                                                     |
| 0.2.8    | Endring/Slettet   | en konstruktør i `OpprettAvvikshendelse`                                                                                                                              |
| 0.2.8    | Endring/Opprettet | `OpprettAvvikshendelse.oppgaveId`                                                                                                                                     |
| 0.2.8    | Endring/Opprettet | `OpprettAvvikshendelse.oppgavetype`                                                                                                                                   |
| 0.2.8    | Endring/Opprettet | `OpprettAvvikshendelse.tema`                                                                                                                                          |
| 0.2.8    | Endring/Opprettet | `OpprettAvvikshendelse.tildelesEnhetsnr`                                                                                                                              |
| 0.2.8    | Endring/Slettet   | `OpprettAvvikshendelse.beskrivelse`                                                                                                                                   |
| 0.2.7    | Endring           | `Avvikshendelse` kan hente `AvvikType` fra streng property                                                                                                            |
| 0.2.6    | Ingen             | Feil i release                                                                                                                                                        |
| 0.2.5    | Opprettet         | `OpprettAvvikshendelseResponse`                                                                                                                                       |
| 0.2.4    | Slettet           | `BestillOrginal`. `Avvikshendelse` er ikke et abstrakt objekt                                                                                                         |
| 0.2.3    | Opprettet         | `BestillOrginal` som er en `Avvikshendelse`                                                                                                                           |
| 0.2.3    | Opprettet         | `Avvikshendelse` og `AvvikType`                                                                                                                                       |
| 0.2.2    | Endring           | `DokumentTilgangResponse` har no-args constructor for json (defaults to empty strings)                                                                                |
| 0.2.1    | Slettet           | `DokumentTilgangRequest`                                                                                                                                              |
| 0.2.0    | Endring           | `DokumentTilganglRequest`/`DokumentTilgangResponse`:  feltene kan ikke være null                                                                                      |
| 0.2.0    | Endring           | `DokumentTilgangRequest`:  fjernet `saksbehandler` som del av request                                                                                                 |
| 0.2.0    | Endring           | `DokumentUrlDto` -> `DokumentTilgangResponse`                                                                                                                         |
| 0.1.0    | Endring           | `AktorDto`: Fjernet all logikk som omhandler identtype                                                                                                                |
| 0.1.0    | Endring           | `AktorDto`: Feltet `personinfo` er fjernet                                                                                                                            |
| 0.1.0    | Endring           | `JournalpostDto`: Feltet `bidragssaker` og tilhørende objekt `BidragSakDto` er fjernet                                                                                |
| 0.0.125  | Endring           | `AktorDto`: Feltet `ident` kan ikke være null og må derfor ha en no-arg konstruktør                                                                                   |
| 0.0.124  | feil release      | ingen endring fra forrige versjon                                                                                                                                     |
| 0.0.123  | Endring           | `AktorDto`: Feltet `personinfo` er et `var`-felt                                                                                                                      |
| 0.0.122  | Endring           | `AktorDto`: Fjernet feltene aktorType og identType                                                                                                                    |
| 0.0.121  | Slettet           | `OrganisasjonDto`                                                                                                                                                     |
| 0.0.121  | Endring           | `PersonDto`, fjernet aktør som moderklassse, samt fjernet feltet `personident`                                                                                        |
| 0.0.121  | Endring           | `AktorDto`, fjernet metode `fetchPerson()`                                                                                                                            |
| 0.0.121  | Endring           | `AktorDto`, nytt felt: `personinfo` - brukes til å berike aktør med person info når aktor er en person                                                                |
| 0.0.120  | Endring           | `JournalpostDto`, felt kan være null: `feilfort` - feilført på bidragssak                                                                                             |
| 0.0.119  | Endring           | `JournalpostDto`, nytt felt: `feilfort` - feilført på bidragssak                                                                                                      |
| 0.0.118  | Opprettet         | `DokumentTilgangRequest`: Forespørsel etter visning av et dokument                                                                                                    |
| 0.0.117  | Endring           | `JournalpostDto`: Fjernet feltet saksnummer                                                                                                                           |
| 0.0.116  | Endring           | Fjernet guava som transitiv avhengighet                                                                                                                               |
| 0.0.115  | Opprettet         | `DokumentUrlDto` for url til et fysisk dokument                                                                                                                       |
| 0.0.114  | Endring           | `bidrag-dokument-dto` - jdk: builds on release 12                                                                                                                     |
| 0.0.113  | Endring           | `EndreJournalpostCommandDto`: oppdatering av `dokumentdato`                                                                                                           |
| 0.0.112  | Endring           | `AktorDto`: metode `fetchPerson()`                                                                                                                                    |
| 0.0.111  | Endring           | `AktorDto`: ny konstruktør: `AktorDto(ident, type)`                                                                                                                   |
| 0.0.110  | Endring           | `AktorDto`: ny metode: `fetchPersonIdentType()`                                                                                                                       |
| 0.0.108  | Endring           | `AktorDto`: metode `erPerson()` kaster ikke exception                                                                                                                 |
| 0.0.106  | Endring           | `AktorDto`: no-args konstruktør                                                                                                                                       |
| 0.0.104  | Endring           | `JournalpostDto`: `gjelderBrukerId` er nå `AktorDto` (person eller organisasjon)                                                                                      |
| 0.0.104  | Opprettet         | `AktorDto`, samt subtyper `PersonDto` og `OrganisasjonDto`                                                                                                            |
| 0.0.103  | Endring           | `NyJournalpostCommendDto`: avsenders navn                                                                                                                             |
| 0.0.101  | Endring           | `EndreJournalpostCommandDto` og `JournalpostDto`: avsenders navn (med fornavn etter komma)                                                                            |
| 0.0.98   | Endring           | `JournalpostDto`: 2 ekstra felter dokumentType OG journalstatus                                                                                                       |
| 0.0.95   | Endring           | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`: fjernet correlation id                                                                   |
| 0.0.95   | Slettet           | `CorrelationIdGenerator`, web-filter for dette er opprettet i `bidrag-commons`                                                                                        |
| 0.0.93   | Endring           | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`:  missing default value for correlation id in constructor                                 |
| 0.0.91   | Endring           | `CorrelationIdGenerator`: generateCorrelationId -> generateMissingCorrelationId                                                                                       |
| 0.0.90   | Endring           | `CorrelationIdGenerator`: CorrelationIdGenerator har en payloadId                                                                                                     |
| 0.0.88   | Endring           | `CorrelationIdGenerator`. MedDtoId -> CorrelationIdGenerator                                                                                                          |
| 0.0.86   | Opprettet         | `MedDtoId`, interface for beskrivelse av dto id                                                                                                                       |
| 0.0.84   | Endring           | `JournalpostDto`, dtoer med får unik id som ikke er null                                                                                                              |
| 0.0.82   | Endring           | `EndreJournalpostCommandDto`: journalpost id fra int til streng                                                                                                       |
| 0.0.78   | Endring           | `JournalpostDto`: kategorikode for saken (N eller U) er lagt til                                                                                                      |
| 0.0.74   | Endring           | `JournalpostDto`: saksnummer skal ikke være være et heltall, men en streng                                                                                            |
| 0.0.62   | Opprettet         | `BidragSakDto` og `RolleDto`                                                                                                                                          |
| 0.0.57   | Endring           | `JournalpostDto`: fjernet property `hello` på `JournalpostDto` som var read only + swagger dok                                                                        |
| 0.0.56   | Endring           | `NyJournalpostCommandDto`: la til fagomrade (bidrag/farskap)                                                                                                          |
| 0.0.51   | Opprettet         | `NyJournalpostCommandDto` og `EndreJournalpostCommandDto`, erstatter `BrevlagerJournalpostDto`                                                                        |
| 0.0.2    | Endring           | `JournalpostDto`: `saksnummerBidrag` og `saksnummerGsak` er nå `saksnummer` med prefiks                                                                               |
| 0.0.1    | Opprettet         | `JournalpostDto` og `BrevlagerJournalpostDto`                                                                                                                         |
