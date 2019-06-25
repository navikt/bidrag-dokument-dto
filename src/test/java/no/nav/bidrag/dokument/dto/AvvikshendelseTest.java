package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AvvikshendelseTest {

  @Test
  @DisplayName("skal hente AvvikType fra streng")
  void skalHenteAvvikTypeFraStreng() {
    var funnetAvvik = new Avvikshendelse(AvvikType.BESTILL_ORGINAL).hent();
    var manglendeAvvik = new Avvikshendelse().hent();

    assertAll(
        () -> assertThat(funnetAvvik).isPresent(),
        () -> assertThat(manglendeAvvik).isEmpty()
    );
  }
}
