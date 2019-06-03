package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AktorDtoTest {

  @Test
  @DisplayName("skal gi personidenttype som bnr når siffer 3 og 4 er et tall høyere enn 20")
  void skalGiIdenttypeBnrNarManedStrengErStorreEnn20() {
    assertThat(new AktorDto("06217412345").fetchIdentType()).isEqualTo("bnr");
  }

  @Test
  @DisplayName("skal gi personidenttype som NorskIdent når 11 siffer")
  void skalGiIdenttypeNorskIdentNarElleveSiffer() {
    assertThat(new AktorDto("06127412345").fetchIdentType()).isEqualTo("NorskIdent");
  }

  @Test
  @DisplayName("skal gi organisasjonidenttype som orgnr når ident er 9 siffer")
  void skalGiIdenttypeOrgnrNarNiSiffer() {
    assertThat(new AktorDto("123456789").fetchIdentType()).isEqualTo("orgnr");
  }

  @Test
  @DisplayName("skal ikke hente personident når identen inneholder andre tegn enn tall")
  void skalIkkeHentePersonIdentVedAndreTegnEnnTall() {
    AktorDto aktorDto = new AktorDto("061274~2345");

    assertThat(aktorDto.fetchPersonIdentType()).isEmpty();
  }

  @Test
  @DisplayName("skal hente personident som AKTOERID når identen har tall men ikke lengde som er 11")
  void skalHentePersonIdentSomAktoerId() {
    AktorDto aktorDto = new AktorDto("061274");

    assertThat(aktorDto.fetchPersonIdentType()).isEqualTo(Optional.of("AKTOERID"));
  }

  @Test
  @DisplayName("skal hente personident som bnr når identen har 11 tall og tall 3 og 4 har verdi over 20")
  void skalHentePersonIdentSomBnr() {
    AktorDto aktorDto = new AktorDto("06217412345");

    assertThat(aktorDto.fetchPersonIdentType()).isEqualTo(Optional.of("bnr"));
  }

  @Test
  @DisplayName("skal hente personident som NorskIdent når identen har 11 tall og tall 3 og 4 har verdi mindre enn 21")
  void skalHentePersonIdentSomNorskIdent() {
    AktorDto aktorDto = new AktorDto("06207412345");

    assertThat(aktorDto.fetchPersonIdentType()).isEqualTo(Optional.of("NorskIdent"));
  }
}
