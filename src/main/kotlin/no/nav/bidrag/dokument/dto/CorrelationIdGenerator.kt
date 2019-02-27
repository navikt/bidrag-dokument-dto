package no.nav.bidrag.dokument.dto

import java.lang.Long.toHexString

interface CorrelationIdGenerator {
    fun payloadId(): String
    fun generateCorrelationId(correlationId: String?): String {
        if (correlationId == null) {
            return toHexString(System.currentTimeMillis()) + '(' + payloadId() + ')'
        }

        return correlationId
    }
}

