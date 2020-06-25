package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonMappingTest {

  @Autowired
  private ObjectMapper objectMapper;

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
  @DisplayName("skal hente Avvikshendelse.detaljer som json")
  void skalHenteAvvikshendelseDetaljerSomJson() throws JsonProcessingException {
    var avvikshendelse = new Avvikshendelse(
        AvvikType.ENDRE_FAGOMRADE.name(), "123", "FAR", Map.of("bekreftetSendtScanning", "true"), "1234567"
    );

    var json = objectMapper.writeValueAsString(avvikshendelse);

    assertThat(json).containsSequence("bekreftetSendtScanning").containsSequence("true");
  }
}
