package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
}
