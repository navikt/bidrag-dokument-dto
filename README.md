# bidrag-dokument-dto

Artifakt inneholder dto'er som er felles for bidrag-dokument

## release endringer

versjon | endringstype | beskrivelse
--------|--------------|------------------------
-       | Opprettet    | `DokumentUrlDto` for url til et fysisk dokument
0.0.114 | Endring      | `bidrag-dokument-dto` - jdk: builds on release 12
0.0.113 | Endring      | `EndreJournalpostCommandDto`: oppdatering av `dokumentdato`
0.0.112 | Endring      | `AktorDto`: metode `fetchPerson()`
0.0.111 | Endring      | `AktorDto`: ny konstruktør: `AktorDto(ident, type)`
0.0.110 | Endring      | `AktorDto`: ny metode: `fetchPersonIdentType()`
0.0.108 | Endring      | `AktorDto`: metode `erPerson()` kaster ikke exception
0.0.106 | Endring      | `AktorDto`: no-args konstruktør
0.0.104 | Endring      | `JournalpostDto`: `gjelderBrukerId` er nå `AktorDto` (person eller organisasjon)
0.0.104 | Opprettet    | `AktorDto`, samt subtyper `PersonDto` og `OrganisasjonDto` 
0.0.103 | Endring      | `NyJournalpostCommendDto`: avsenders navn
0.0.101 | Endring      | `EndreJournalpostCommandDto` og `JournalpostDto`:avsenders navn (med fornavn etter komma)
0.0.98  | Endring      | `JournalpostDto`: 2 ekstra felter dokumentType OG journalstatus
0.0.95  | Endring      | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`: fjernet correlation id
0.0.95  | Slettet      | `CorrelationIdGenerator`
0.0.93  | Endring      | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`:  missing default value for correlation id in constructor
0.0.91  | Endring      | `CorrelationIdGenerator`: generateCorrelationId -> generateMissingCorrelationId
0.0.90  | Endring      | `CorrelationIdGenerator`: CorrelationIdGenerator har en payloadId
0.0.88  | Opprettet    | `CorrelationIdGenerator`. MedDtoId -> CorrelationIdGenerator
0.0.86  | | interface for beskrivelse av dto id
0.0.84  | | dtoer med får unik id som ikke er null
0.0.82  | | journalpost id fra int til streng i EndreJournalpostDtoCommand
0.0.78  | | kategorikode for saken (N eller U) er lagt til
0.0.74  | | saksnummer skal ikke være være et heltall, men en streng
0.0.73  | | opprettet `BidragSakDto`og `RolleDto`
0.0.67  | | flyttet (slettet) `BidragSakDto`og `RolleDto` til navikt/bidrag-sak-dto
0.0.62  | | opprettet `BidragSakDto` og `RolleDto`
0.0.57  | | fjernet property `hello` på `JournalpostDto` som var read only + swagger dok
0.0.56  | | la til fagomrade i `NyJournalpostCommandDto` (bidrag/farskap)
0.0.51  | | erstattet `BrevlagerJournalpostDto` med `NyJournalpostCommandDto` og `EndreJournalpostCommandDto`
0.0.2   | | `saksnummerBidrag` og `saksnummerGsak` er nå `saksnummer` med prefiks
0.0.1   | | opprettet `JournalpostDto` og `BrevlagerJournalpostDto`
