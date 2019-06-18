# bidrag-dokument-dto

Artifakt inneholder dto'er som er felles for bidrag-dokument

## release endringer

versjon | endringstype | beskrivelse
--------|--------------|------------------------
0.2.0 ? | Endring      | `DokumentUrlRequest`/`DokumentUrlResponse`:  feltene kan ikke være null
0.2.0 ? | Endring      | `DokumentUrlRequest`:  fjernet `saksbehandler` som del av request
0.2.0 ? | Endring      | `DokumentUrlDto` -> `DokumentUrlResponse`
  0.1.0 | Endring      | `AktorDto`: Fjernet all logikk som omhandler identtype
  0.1.0 | Endring      | `AktorDto`: Feltet `personinfo` er fjernet
  0.1.0 | Endring      | `JournalpostDto`: Feltet `bidragssaker` og tilhørende objekt `BidragSakDto` er fjernet
0.0.125 | Endring      | `AktorDto`: Feltet `ident` kan ikke være null og må derfor ha en no-arg konstruktør
0.0.124 | feil release | ingen endring fra forrige versjon
0.0.123 | Endring      | `AktorDto`: Feltet `personinfo` er et `var`-felt
0.0.122 | Endring      | `AktorDto`: Fjernet feltene aktorType og identType
0.0.121 | Slettet      | `OrganisasjonDto`
0.0.121 | Endring      | `PersonDto`, fjernet aktør som moderklassse, samt fjernet feltet `personident`
0.0.121 | Endring      | `AktorDto`, fjernet metode `fetchPerson()`
0.0.121 | Endring      | `AktorDto`, nytt felt: `personinfo` - brukes til å berike aktør med person info når aktor er en person
0.0.120 | Endring      | `JournalpostDto`, felt kan være null: `feilfort` - feilført på bidragssak
0.0.119 | Endring      | `JournalpostDto`, nytt felt: `feilfort` - feilført på bidragssak
0.0.118 | Opprettet    | `DokumentTilgangRequest`: Forespørsel etter visning av et dokument
0.0.117 | Endring      | `JournalpostDto`: Fjernet feltet saksnummer
0.0.116 | Endring      | Fjernet guava som transitiv avhengighet
0.0.115 | Opprettet    | `DokumentUrlDto` for url til et fysisk dokument
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
0.0.101 | Endring      | `EndreJournalpostCommandDto` og `JournalpostDto`: avsenders navn (med fornavn etter komma)
 0.0.98 | Endring      | `JournalpostDto`: 2 ekstra felter dokumentType OG journalstatus
 0.0.95 | Endring      | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`: fjernet correlation id
 0.0.95 | Slettet      | `CorrelationIdGenerator`
 0.0.93 | Endring      | `NyJournalpostCommendDto`, `EndreJournalpostCommandDto` og `JournalpostDto`:  missing default value for correlation id in constructor
 0.0.91 | Endring      | `CorrelationIdGenerator`: generateCorrelationId -> generateMissingCorrelationId
 0.0.90 | Endring      | `CorrelationIdGenerator`: CorrelationIdGenerator har en payloadId
 0.0.88 | Endring      | `CorrelationIdGenerator`. MedDtoId -> CorrelationIdGenerator
 0.0.86 | Opprettet    | `MedDtoId`, interface for beskrivelse av dto id
 0.0.84 | Endring      | `JournalpostDto`, dtoer med får unik id som ikke er null
 0.0.82 | Endring      | `EndreJournalpostCommandDto`: journalpost id fra int til streng
 0.0.78 | Endring      | `JournalpostDto`: kategorikode for saken (N eller U) er lagt til
 0.0.74 | Endring      | `JournalpostDto`: saksnummer skal ikke være være et heltall, men en streng
 0.0.62 | Opprettet    | `BidragSakDto` og `RolleDto`
 0.0.57 | Endring      | `JournalpostDto`: fjernet property `hello` på `JournalpostDto` som var read only + swagger dok
 0.0.56 | Endring      | `NyJournalpostCommandDto`: la til fagomrade (bidrag/farskap)
 0.0.51 | Opprettet    | `NyJournalpostCommandDto` og `EndreJournalpostCommandDto`, erstatter `BrevlagerJournalpostDto`
  0.0.2 | Endring      | `JournalpostDto`: `saksnummerBidrag` og `saksnummerGsak` er nå `saksnummer` med prefiks
  0.0.1 | Opprettet    | `JournalpostDto` og `BrevlagerJournalpostDto`
