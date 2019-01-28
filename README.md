# bidrag-dokument-dto

Artifakt inneholder dto'er som er felles for bidrag-dokument

## release endringer

versjon | beskrivelse
-----------|------------------------
0.0.73 | opprettet `BidragSakDto`og `RolleDto`
0.0.67 | flyttet (slettet) `BidragSakDto`og `RolleDto` til navikt/bidrag-sak-dto
0.0.62 | opprettet `BidragSakDto` og `RolleDto`
0.0.57 | fjernet property `hello` på `JournalpostDto` som var read only + swagger dok
0.0.56 | la til fagomrade i `NyJournalpostCommandDto` (bidrag/farskap)
0.0.51 | erstattet `BrevlagerJournalpostDto` med `NyJournalpostCommandDto` og `EndreJournalpostCommandDto`
0.0.2 | `saksnummerBidrag` og `saksnummerGsak` er nå `saksnummer` med prefiks
0.0.1 | opprettet `JournalpostDto` og `BrevlagerJournalpostDto`
