package no.nav.bidrag.dokument.dto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class JournalpostIdTest {

    @Test
    fun `skal parse journalpostId med ukjent prefix`(){
        val journalpostId = JournalpostId("BAA-123213213")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe false
        journalpostId.id shouldBe "BAA-123213213"
        journalpostId.idNumerisk shouldBe 123213213
    }

    @Test
    fun `skal parse journalpostId uten prefix`(){
        val journalpostId = JournalpostId("DDD:123213213")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe false
        journalpostId.id shouldBe "DDD:123213213"
        journalpostId.idNumerisk shouldBe 123213213
    }

    @Test
    fun `skal parse journalpostId for midlertidlig brevlager`(){
        val journalpostId = JournalpostId("BID-123213213")

        journalpostId.erSystemBidrag shouldBe true
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe false
        journalpostId.id shouldBe "BID-123213213"
        journalpostId.idNumerisk shouldBe 123213213
    }

    @Test
    fun `skal parse journalpostId for forsendelse`(){
        val journalpostId = JournalpostId("BIF-123213213")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe true
        journalpostId.id shouldBe "BIF-123213213"
        journalpostId.idNumerisk shouldBe 123213213
    }

    @Test
    fun `skal parse journalpostId for joark`(){
        val journalpostId = JournalpostId("JOARK-123213213")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe true
        journalpostId.erSystemForsendelse shouldBe false
        journalpostId.id shouldBe "JOARK-123213213"
        journalpostId.idNumerisk shouldBe 123213213
    }

    @Test
    fun `skal parse journalpostId som er null`(){
        val journalpostId = JournalpostId(null)

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe false

        journalpostId.id shouldBe null
        journalpostId.idNumerisk shouldBe null
    }

    @Test
    fun `skal parse journalpostId for midlertidlig brevlager som er numerisk`(){
        val journalpostId = JournalpostId("39010946")

        journalpostId.erSystemBidrag shouldBe true
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe false
    }

    @Test
    fun `skal parse journalpostId for joark som er numerisk`(){
        val journalpostId = JournalpostId("598113714")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe true
        journalpostId.erSystemForsendelse shouldBe false
    }

    @Test
    fun `skal parse journalpostId for forsendelse som er numerisk`(){
        val journalpostId = JournalpostId("1000000085")

        journalpostId.erSystemBidrag shouldBe false
        journalpostId.erSystemJoark shouldBe false
        journalpostId.erSystemForsendelse shouldBe true
    }
}