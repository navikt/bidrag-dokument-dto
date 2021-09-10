package no.nav.bidrag.dokument.dto

class JournalpostBeskrivelseException(message: String) : RuntimeException(message)
class JournalpostHendelseException(message: String, throwable: Throwable?) : RuntimeException(message, throwable) {
    constructor(message: String) : this(message = message, throwable = null)
}

class JournalpostIkkeFunnetException(message: String) : RuntimeException(message)
class OppgaveException(message: String) : RuntimeException(message)
class OppgaveIkkeOpprettetException(message: String) : RuntimeException(message)
class TokenException(message: String) : RuntimeException(message)
