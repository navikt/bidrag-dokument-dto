package no.nav.bidrag.dokument.dto

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class JsonMappingTest {
    private val objectMapper: ObjectMapper = ObjectMapper().findAndRegisterModules()

    @Nested
    internal inner class FraJson {
        @Test
        fun `skal mappe Avvikshendelse`() {
            val avvikshendelse = """
          {
            "avvikType":"BESTILL_ORIGINAL",
            "detaljer": {
              "enhetsnummer":"4806"
            }
          }
          
          """.trimIndent()
            val hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse::class.java)
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(hendelsen).`as`("avvikshendelse").isNotNull() },
                 {
                    Assertions.assertThat(hendelsen.detaljer).`as`("avvikshendelse.detaljer.enhetsnummer")
                        .containsKey("enhetsnummer").extracting("enhetsnummer").isEqualTo("4806")
                },
                 { Assertions.assertThat(hendelsen.avvikType).`as`("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_ORIGINAL.name) },
                 {
                    Assertions.assertThat(hendelsen.hent()).`as`("avvikshendelse.avvikType (som enum)").isPresent.get()
                        .isEqualTo(AvvikType.BESTILL_ORIGINAL)
                }
            )
        }

        @Test
        fun `skal mappe Avvikshendelse med beskrivelse`() {
            val avvikshendelse = """ 
          {
            "avvikType":"BESTILL_SPLITTING",
            "beskrivelse":"avsnitt 2",
            "detaljer": {
              "enhetsnummer":"4806"
            }
          }
          
          """.trimIndent()
            val hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse::class.java)
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(hendelsen).`as`("avvikshendelse").isNotNull() },
                 {
                    Assertions.assertThat(hendelsen.detaljer).`as`("avvikshendelse.detaljer.enhetsnummer")
                        .extracting("enhetsnummer").isEqualTo("4806")
                },
                 {
                    Assertions.assertThat(hendelsen.avvikType).`as`("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_SPLITTING.name)
                },
                 {
                    Assertions.assertThat(hendelsen.hent()).`as`("avvikshendelse.avvikType (som enum)").isPresent.get()
                        .isEqualTo(AvvikType.BESTILL_SPLITTING)
                },
                 { Assertions.assertThat(hendelsen.beskrivelse).`as`("avvikshendelse.beskrivelse").isEqualTo("avsnitt 2") }
            )
        }

        @Test
        fun `skal mappe Avvikshendelse med detaljer`() {
            val avvikshendelse = """ 
          {
            "avvikType":"BESTILL_SPLITTING",
            "detaljer":{
              "fagomrade":"BID"
            }
          }
          
          """.trimIndent()
            val hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse::class.java)
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(hendelsen).`as`("avvikshendelse").isNotNull() },
                 {
                    Assertions.assertThat(hendelsen.avvikType).`as`("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_SPLITTING.name)
                },
                 {
                    Assertions.assertThat(hendelsen.hent()).`as`("avvikshendelse.avvikType (som enum)").isPresent.get()
                        .isEqualTo(AvvikType.BESTILL_SPLITTING)
                },
                 { Assertions.assertThat(hendelsen.detaljer).`as`("avvikshendelse.detaljer").isEqualTo(mapOf("fagomrade" to "BID")) }
            )
        }

        @Test
        fun `skal mappe til EndreJournalpostCommand`() {
            val json = """
          {
            "skalJournalfores":true,
            "gjelder":"25018543055",
            "tittel":"journalfør",
            "tilknyttSaker":["0703467"],
            "endreDokumenter": [],
            "fagomrade":"BID",
            "dokumentDato":"2020-01-01"
          }
          
          """.trimIndent()
            val endreJournalpostCommand = objectMapper.readValue(json, EndreJournalpostCommand::class.java)
            Assertions.assertThat(endreJournalpostCommand).`as`("endre command").isNotNull
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(endreJournalpostCommand.skalJournalfores).`as`("skal journalføres").isEqualTo(true) },
                 { Assertions.assertThat(endreJournalpostCommand.gjelder).`as`("gjelder").isEqualTo("25018543055") },
                 { Assertions.assertThat(endreJournalpostCommand.tittel).`as`("tittel").isEqualTo("journalfør") },
                 { Assertions.assertThat(endreJournalpostCommand.tilknyttSaker).`as`("tilknytt saker").isEqualTo(listOf("0703467")) },
                 { Assertions.assertThat(endreJournalpostCommand.endreDokumenter).`as`("endre dokumenter").isEmpty() },
                 { Assertions.assertThat(endreJournalpostCommand.fagomrade).`as`("fagomrade").isEqualTo("BID") },
                 { Assertions.assertThat(endreJournalpostCommand.dokumentDato).`as`("dokument dato").isEqualTo(LocalDate.of(2020, 1, 1)) }
            )
        }

        @Test
        fun `skal mappe aktør fra json uten type`() {
            val json = """
          {
            "ident": "06127412345"
          }
          
          """.trimIndent()
            val aktorDto = objectMapper.readValue(json, AktorDto::class.java)
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(aktorDto.ident).`as`("ident").isEqualTo("06127412345") },
                 { Assertions.assertThat(aktorDto.type).`as`("type").isNull() },
                 { Assertions.assertThat(aktorDto.hentIdentType()).`as`("identtype").isNull() }
            )
        }

        @Test
        fun `skal mappe journalposthendelse med type notat og forsendelse`() {
            @Language("json") val json = """
          {
            "journalpostId": "BIF-13123213",
            "journalposttype": "X",
            "journalstatus": "KP",
            "fagomrade": "BID",
            "tema": "BID"
          }
          
          """.trimIndent()


            val journalpostHendelse = objectMapper.readValue(json, JournalpostHendelse::class.java)
            journalpostHendelse.journalstatus shouldBe "KP"
            journalpostHendelse.fagomrade shouldBe "BID"
            journalpostHendelse.tema shouldBe "BID"
            journalpostHendelse.hentStatus() shouldBe JournalpostStatus.KLAR_FOR_DISTRIBUSJON
            journalpostHendelse.hentJournalposttype() shouldBe JournalpostType.NOTAT
            journalpostHendelse.hentJournalposttype() shouldBe JournalpostType.NOTAT
            journalpostHendelse.erForsendelse() shouldBe true
            journalpostHendelse.erBidragJournalpost() shouldBe false
            journalpostHendelse.erJoarkJournalpost() shouldBe false

            @Language("json")
            val json2 = """
          {
            "journalpostId": "BID-13123213",
            "journalposttype": "NOTAT",
            "journalstatus": "KLAR_FOR_DISTRIBUSJON"
          }
          
          """.trimIndent()
            val journalpostHendelse2 = objectMapper.readValue(json2, JournalpostHendelse::class.java)
            journalpostHendelse2.hentJournalposttype() shouldBe JournalpostType.NOTAT
            journalpostHendelse2.journalstatus shouldBe "KLAR_FOR_DISTRIBUSJON"
            journalpostHendelse2.hentStatus() shouldBe JournalpostStatus.KLAR_FOR_DISTRIBUSJON
        }

        @Test
        fun `skal mappe journalposthendelse med type utgående og bidrag`() {
            @Language("json")
            val json = """
          {
            "journalpostId": "BID-13123213",
            "journalposttype": "UTGÅENDE",
            "status": "KLAR_FOR_DISTRIBUSJON"
          }
          
          """.trimIndent()


            val journalpostHendelse = objectMapper.readValue(json, JournalpostHendelse::class.java)
            journalpostHendelse.hentJournalposttype() shouldBe JournalpostType.UTGÅENDE
            journalpostHendelse.erForsendelse() shouldBe false
            journalpostHendelse.erBidragJournalpost() shouldBe true
            journalpostHendelse.erJoarkJournalpost() shouldBe false
            journalpostHendelse.hentStatus() shouldBe JournalpostStatus.KLAR_FOR_DISTRIBUSJON
            journalpostHendelse.erUtgående() shouldBe true


            @Language("json")
            val json2 = """
          {
            "journalpostId": "BID-13123213",
            "journalposttype": "U"
          }
          
          """.trimIndent()
            val journalpostHendelse2 = objectMapper.readValue(json2, JournalpostHendelse::class.java)
            journalpostHendelse2.hentJournalposttype() shouldBe JournalpostType.UTGÅENDE
            journalpostHendelse2.erUtgående() shouldBe true
        }

        @Test
        fun `skal mappe journalposthendelse med type inngående og type joark`() {
            @Language("json") val json = """
          {
            "journalpostId": "JOARK-13123213",
            "journalposttype": "INNGÅENDE"
          }
          
          """.trimIndent()
            val journalpostHendelse = objectMapper.readValue(json, JournalpostHendelse::class.java)
            journalpostHendelse.hentJournalposttype() shouldBe JournalpostType.INNGÅENDE
            journalpostHendelse.erForsendelse() shouldBe false
            journalpostHendelse.erBidragJournalpost() shouldBe false
            journalpostHendelse.erJoarkJournalpost() shouldBe true
            journalpostHendelse.erInngående() shouldBe true

            @Language("json")
            val json2 = """
          {
            "journalpostId": "BID-13123213",
            "journalposttype": "I"
          }
          
          """.trimIndent()
            val journalpostHendelse2 = objectMapper.readValue(json2, JournalpostHendelse::class.java)
            journalpostHendelse2.hentJournalposttype() shouldBe JournalpostType.INNGÅENDE
            journalpostHendelse2.erInngående() shouldBe true
        }
    }

    @Nested
    internal inner class TilJson {
        @Test
        fun `skal hente Avvikshendelse detaljer som json`() {
            val avvikshendelse = Avvikshendelse(
                AvvikType.ENDRE_FAGOMRADE.name, mapOf("fagomrade" to "FAR", "bekreftetSendtScanning" to "true"), "1234567"
            )
            val json = objectMapper.writeValueAsString(avvikshendelse)
            Assertions.assertThat(json).containsSequence("bekreftetSendtScanning").containsSequence("true")
        }
    }

    @Nested
    @DisplayName("til og fra json")
    internal inner class TilFraJson {
        @Test
        fun `skal mappe JournalpostHendelse til json og mappe tilbake til objekt `() {
            val journalpostHendelse = JournalpostHendelse(
                "journalpostId",
                "aktorId",
                "fnr",
                "behandlingstema",
                "tittel",
                "tema",
                "tema",
                "batchId",
                "jptype",
                HendelseType.JOURNALFORING,
                "s",
                "enhet",
                "journalstatus",
                Sporingsdata("xyz", "jb", "Jon Blund", "1001"),
                ArrayList(),
                null, null
            )
            val json = objectMapper.writeValueAsString(journalpostHendelse)
            val fraJson = objectMapper.readValue(json, JournalpostHendelse::class.java)
            Assertions.assertThat(fraJson).`as`("journalpostHendelse fra json").isNotNull
            org.junit.jupiter.api.Assertions.assertAll(
                 { Assertions.assertThat(fraJson.journalpostId).`as`("journalpostId").isEqualTo(journalpostHendelse.journalpostId) },
                 { Assertions.assertThat(fraJson.aktorId).`as`("aktorId").isEqualTo(journalpostHendelse.aktorId) },
                 { Assertions.assertThat(fraJson.enhet).`as`("enhet").isEqualTo(journalpostHendelse.enhet) },
                 { Assertions.assertThat(fraJson.fagomrade).`as`("fagomrade").isEqualTo(journalpostHendelse.fagomrade) },
                 {
                    Assertions.assertThat(fraJson.journalstatus).`as`("journalstatus")
                        .isEqualTo(journalpostHendelse.journalstatus)
                }
            )
            val sporing = fraJson.sporing
            Assertions.assertThat(sporing).`as`("sporing fra json").isNotNull
            org.junit.jupiter.api.Assertions.assertAll(
                 {
                    Assertions.assertThat(sporing!!.brukerident).`as`("brukerident").isEqualTo(
                        journalpostHendelse.sporing!!.brukerident
                    )
                },
                 {
                    Assertions.assertThat(sporing!!.correlationId).`as`("correlationId").isEqualTo(
                        journalpostHendelse.sporing!!.correlationId
                    )
                },
                 {
                    Assertions.assertThat(sporing!!.saksbehandlersNavn).`as`("saksbehandlers navn")
                        .isEqualTo(journalpostHendelse.sporing!!.saksbehandlersNavn)
                }
            )
        }
    }
}