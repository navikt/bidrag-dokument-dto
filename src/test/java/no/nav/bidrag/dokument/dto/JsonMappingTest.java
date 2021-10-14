package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonMappingTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  @DisplayName("fra json")
  class FraJson {

    @Test
    @DisplayName("skal mappe Avvikshendelse")
    void skalMappeAvvikshendelse() throws IOException {
      var avvikshendelse = """
          {
            "avvikType":"BESTILL_ORIGINAL",
            "detaljer": {
              "enhetsnummer":"4806"
            }
          }
          """;

      var hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse.class);

      assertAll(
          () -> assertThat(hendelsen).as("avvikshendelse").isNotNull(),
          () -> assertThat(hendelsen.getDetaljer()).as("avvikshendelse.detaljer.enhetsnummer")
              .containsKey("enhetsnummer").extracting("enhetsnummer").isEqualTo("4806"),
          () -> assertThat(hendelsen.getAvvikType()).as("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_ORIGINAL.name()),
          () -> assertThat(hendelsen.hent()).as("avvikshendelse.avvikType (som enum)").isPresent().get().isEqualTo(AvvikType.BESTILL_ORIGINAL)
      );
    }

    @Test
    @DisplayName("skal mappe Avvikshendelse med beskrivelse")
    void skalMappeAvvikshendelseMedBeskrivelse() throws IOException {
      var avvikshendelse = """ 
          {
            "avvikType":"BESTILL_SPLITTING",
            "beskrivelse":"avsnitt 2",
            "detaljer": {
              "enhetsnummer":"4806"
            }
          }
          """;

      var hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse.class);

      assertAll(
          () -> assertThat(hendelsen).as("avvikshendelse").isNotNull(),
          () -> assertThat(hendelsen.getDetaljer()).as("avvikshendelse.detaljer.enhetsnummer")
              .extracting("enhetsnummer").isEqualTo("4806"),
          () -> assertThat(hendelsen.getAvvikType()).as("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_SPLITTING.name()),
          () -> assertThat(hendelsen.hent()).as("avvikshendelse.avvikType (som enum)").isPresent().get().isEqualTo(AvvikType.BESTILL_SPLITTING),
          () -> assertThat(hendelsen.getBeskrivelse()).as("avvikshendelse.beskrivelse").isEqualTo("avsnitt 2")
      );
    }

    @Test
    @DisplayName("skal mappe Avvikshendelse med detaljer")
    void skalMappeAvvikshendelseMedDetaljer() throws IOException {
      var avvikshendelse = """ 
          {
            "avvikType":"BESTILL_SPLITTING",
            "detaljer":{
              "fagomrade":"BID"
            }
          }
          """;

      var hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse.class);

      assertAll(
          () -> assertThat(hendelsen).as("avvikshendelse").isNotNull(),
          () -> assertThat(hendelsen.getAvvikType()).as("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_SPLITTING.name()),
          () -> assertThat(hendelsen.hent()).as("avvikshendelse.avvikType (som enum)").isPresent().get().isEqualTo(AvvikType.BESTILL_SPLITTING),
          () -> assertThat(hendelsen.getDetaljer()).as("avvikshendelse.detaljer").isEqualTo(Map.of("fagomrade", "BID"))
      );
    }

    @Test
    @DisplayName("skal mappe til EndreJournalpostCommand")
    void skalMappeTilEndreJournalpostCommand() throws JsonProcessingException {
      var json = """
          {
            "skalJournalfores":true,
            "gjelder":"25018543055",
            "tittel":"journalfør",
            "tilknyttSaker":["0703467"],
            "endreDokumenter": [],
            "fagomrade":"BID",
            "dokumentDato":"2020-01-01"
          }
          """;

      var endreJournalpostCommand = objectMapper.readValue(json, EndreJournalpostCommand.class);
      assertThat(endreJournalpostCommand).as("endre command").isNotNull();

      assertAll(
          () -> assertThat(endreJournalpostCommand.getSkalJournalfores()).as("skal journalføres").isEqualTo(true),
          () -> assertThat(endreJournalpostCommand.getGjelder()).as("gjelder").isEqualTo("25018543055"),
          () -> assertThat(endreJournalpostCommand.getTittel()).as("tittel").isEqualTo("journalfør"),
          () -> assertThat(endreJournalpostCommand.getTilknyttSaker()).as("tilknytt saker").isEqualTo(List.of("0703467")),
          () -> assertThat(endreJournalpostCommand.getEndreDokumenter()).as("endre dokumenter").isEmpty(),
          () -> assertThat(endreJournalpostCommand.getFagomrade()).as("fagomrade").isEqualTo("BID"),
          () -> assertThat(endreJournalpostCommand.getDokumentDato()).as("dokument dato").isEqualTo(LocalDate.of(2020, 1, 1))
      );
    }

    @Test
    @DisplayName("skal mappe aktør fra json uten type")
    void skalMappeAktorFraJson() throws JsonProcessingException {
      var json = """
          {
            "ident": "06127412345"
          }
          """;

      var aktorDto = objectMapper.readValue(json, AktorDto.class);

      assertAll(
          () -> assertThat(aktorDto.getIdent()).as("ident").isEqualTo("06127412345"),
          () -> assertThat(aktorDto.getType()).as("type").isNull(),
          () -> assertThat(aktorDto.hentIdentType()).as("identtype").isNull()
      );
    }
  }

  @Nested
  @DisplayName("til json")
  class TilJson {

    @Test
    @DisplayName("skal hente Avvikshendelse.detaljer som json")
    void skalHenteAvvikshendelseDetaljerSomJson() throws JsonProcessingException {
      var avvikshendelse = new Avvikshendelse(
          AvvikType.ENDRE_FAGOMRADE.name(), Map.of("fagomrade", "FAR", "bekreftetSendtScanning", "true"), "1234567"
      );

      var json = objectMapper.writeValueAsString(avvikshendelse);

      assertThat(json).containsSequence("bekreftetSendtScanning").containsSequence("true");
    }
  }

  @Nested
  @DisplayName("til og fra json")
  class TilFraJson {

    @Test
    @DisplayName("skal mappe JournalpostHendelse til json og mappe tilbake til objekt ")
    void skalMappeJournalpostHendelseTilJsonOgTilbakeTilObjekt() throws JsonProcessingException {
      var journalpostHendelse = new JournalpostHendelse(
          "journalpostId",
          "aktorId",
          "fagomrade",
          "enhet",
          "journalstatus",
          new Sporingsdata("xyz", "jb", "Jon Blund")
      );

      var json = objectMapper.writeValueAsString(journalpostHendelse);
      var fraJson = objectMapper.readValue(json, JournalpostHendelse.class);

      assertThat(fraJson).as("journalpostHendelse fra json").isNotNull();

      assertAll(
          () -> assertThat(fraJson.getJournalpostId()).as("journalpostId").isEqualTo(journalpostHendelse.getJournalpostId()),
          () -> assertThat(fraJson.getAktorId()).as("aktorId").isEqualTo(journalpostHendelse.getAktorId()),
          () -> assertThat(fraJson.getEnhet()).as("enhet").isEqualTo(journalpostHendelse.getEnhet()),
          () -> assertThat(fraJson.getFagomrade()).as("fagomrade").isEqualTo(journalpostHendelse.getFagomrade()),
          () -> assertThat(fraJson.getJournalstatus()).as("journalstatus")
              .isEqualTo(journalpostHendelse.getJournalstatus())
      );

      var sporing = fraJson.getSporing();

      assertThat(sporing).as("sporing fra json").isNotNull();

      //noinspection ConstantConditions
      assertAll(
          () -> assertThat(sporing.getBrukerident()).as("brukerident").isEqualTo(journalpostHendelse.getSporing().getBrukerident()),
          () -> assertThat(sporing.getCorrelationId()).as("correlationId").isEqualTo(journalpostHendelse.getSporing().getCorrelationId()),
          () -> assertThat(sporing.getSaksbehandlersNavn()).as("saksbehandlers navn")
              .isEqualTo(journalpostHendelse.getSporing().getSaksbehandlersNavn())
      );
    }
  }
}
