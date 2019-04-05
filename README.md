# bidrag-dokument-dto

Artifakt inneholder dto'er som er felles for bidrag-dokument

## release endringer

versjon | beskrivelse
--------|------------------------
0.0.104 | `gjelderBrukerId` til `AktorDto` (person eller organisasjon)
0.0.103 | avsenders navn på NyJournalpostCommendDto
0.0.101 | avsenders navn (med fornavn etter komma)
0.0.98 | dokumentType for journalpost, samt journalstatus
0.0.95 | removed correlation id from dtos
0.0.93 | missing default value in constructor
0.0.91 | generateCorrelationId -> generateMissingCorrelationId
0.0.90 | CorrelationIdGenerator har en payloadId
0.0.88 | MedDtoId -> CorrelationIdGenerator
0.0.86 | interface for beskrivelse av dto id
0.0.84 | dtoer med får unik id som ikke er null
0.0.82 | journalpost id fra int til streng i EndreJournalpostDtoCommand
0.0.78 | kategorikode for saken (N eller U) er lagt til
0.0.74 | saksnummer skal ikke være være et heltall, men en streng
0.0.73 | opprettet `BidragSakDto`og `RolleDto`
0.0.67 | flyttet (slettet) `BidragSakDto`og `RolleDto` til navikt/bidrag-sak-dto
0.0.62 | opprettet `BidragSakDto` og `RolleDto`
0.0.57 | fjernet property `hello` på `JournalpostDto` som var read only + swagger dok
0.0.56 | la til fagomrade i `NyJournalpostCommandDto` (bidrag/farskap)
0.0.51 | erstattet `BrevlagerJournalpostDto` med `NyJournalpostCommandDto` og `EndreJournalpostCommandDto`
0.0.2 | `saksnummerBidrag` og `saksnummerGsak` er nå `saksnummer` med prefiks
0.0.1 | opprettet `JournalpostDto` og `BrevlagerJournalpostDto`
