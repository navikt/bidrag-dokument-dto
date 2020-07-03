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
      String avvikshendelse = " {\"avvikType\":\"BESTILL_ORIGINAL\",\"enhetsnummer\":\"4806\"}";

      Avvikshendelse hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse.class);

      assertAll(
          () -> assertThat(hendelsen).as("avvikshendelse").isNotNull(),
          () -> assertThat(hendelsen.getEnhetsnummer()).as("avvikshendelse.enhetsnummer").isEqualTo("4806"),
          () -> assertThat(hendelsen.getAvvikType()).as("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_ORIGINAL.name()),
          () -> assertThat(hendelsen.hent()).as("avvikshendelse.avvikType (som enum)").isPresent().get().isEqualTo(AvvikType.BESTILL_ORIGINAL)
      );
    }

    @Test
    @DisplayName("skal mappe Avvikshendelse med beskrivelse")
    void skalMappeAvvikshendelseMedBeskrivelse() throws IOException {
      String avvikshendelse = " {\"avvikType\":\"BESTILL_SPLITTING\",\"enhetsnummer\":\"4806\", \"beskrivelse\":\"avsnitt 2\"}";

      Avvikshendelse hendelsen = objectMapper.readValue(avvikshendelse, Avvikshendelse.class);

      assertAll(
          () -> assertThat(hendelsen).as("avvikshendelse").isNotNull(),
          () -> assertThat(hendelsen.getEnhetsnummer()).as("avvikshendelse.enhetsnummer").isEqualTo("4806"),
          () -> assertThat(hendelsen.getAvvikType()).as("avvikshendelse.avvikType").isEqualTo(AvvikType.BESTILL_SPLITTING.name()),
          () -> assertThat(hendelsen.hent()).as("avvikshendelse.avvikType (som enum)").isPresent().get().isEqualTo(AvvikType.BESTILL_SPLITTING),
          () -> assertThat(hendelsen.getBeskrivelse()).as("avvikshendelse.beskrivelse").isEqualTo("avsnitt 2")
      );
    }

    @Test
    @DisplayName("skal mappe til EndreJournalpostCommand")
    void skalMappeTilEndreJournalpostCommand() throws JsonProcessingException {
      var json = String.join("\n", "{",
          "\"skalJournalfores\":true,",
          "\"gjelder\":\"25018543055\",",
          "\"tittel\":\"journalfør\",",
          "\"tilknyttSaker\":[\"0703467\"],",
          "\"endreDokumenter\": [],",
          "\"fagomrade\":\"BID\",",
          "\"dokumentDato\":\"2020-01-01\"",
          "}"
      );

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
  }

  @Nested
  @DisplayName("til json")
  class TilJson {

    @Test
    @DisplayName("skal hente Avvikshendelse.detaljer som json")
    void skalHenteAvvikshendelseDetaljerSomJson() throws JsonProcessingException {
      var avvikshendelse = new Avvikshendelse(
          AvvikType.ENDRE_FAGOMRADE.name(), "123", "FAR", Map.of("bekreftetSendtScanning", "true"), "1234567"
      );

      var json = objectMapper.writeValueAsString(avvikshendelse);

      assertThat(json).containsSequence("bekreftetSendtScanning").containsSequence("true");
    }
  }
}
