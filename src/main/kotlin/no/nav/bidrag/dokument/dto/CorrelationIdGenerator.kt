package no.nav.bidrag.dokument.dto

import java.lang.Long.toHexString

interface CorrelationIdGenerator {
    fun generateCorrelationId(correlationId: String?): String {
        if (correlationId == null) {
            return toHexString(System.currentTimeMillis())
        }

        return correlationId
    }
}

