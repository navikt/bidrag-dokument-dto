package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class AvvikshendelseTest {

  @ParameterizedTest
  @DisplayName("skal hente AvvikType fra streng")
  @EnumSource(AvvikType.class)
  void skalHenteAvvikTypeFraStreng(AvvikType avvikType) {
    var funnetAvvik = new Avvikshendelse(avvikType.name(), null, null, Map.of()).hent(); // enum blir lagret som streng i en Avvikshendelse
    var manglendeAvvik = new Avvikshendelse("feil", null, null, Map.of()).hent();

    assertAll(
        () -> assertThat(funnetAvvik).isPresent().get().isEqualTo(avvikType),
        () -> assertThat(manglendeAvvik).isEmpty()
    );
  }
}
