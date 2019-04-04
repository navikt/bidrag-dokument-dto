package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AktorTest {

  @Test
  @DisplayName("skal kunne gi hvilken som helst verdi til ident og identtype")
  void skalGiVerdiTilIdenOgIdenttype() {
    PersonDto personDto = new PersonDto();
    personDto.setIdentType("type");
    assertThat(personDto.getIdentType()).isEqualTo("type");
  }

  @Test
  @DisplayName("skal feile når ident er blank")
  void skalFeileNarIdentErBlank() {
    assertThatIllegalStateException().isThrownBy(() -> new PersonDto("").fetchIdentType())
        .withMessage("Ukjent ident: \"\"");
  }

  @Test
  @DisplayName("skal feile når ident ikke er tall")
  void skalFeileNarIdentIkkeErTall() {
    assertThatIllegalStateException().isThrownBy(() -> new PersonDto("abcdefgh").fetchIdentType())
        .withMessage("Ukjent ident: \"abcdefgh\"");
  }

  @Test
  @DisplayName("skal feile når ident har mindre enn 11 siffer")
  void skalFeileNarIdentHarMindreEnn11Siffer() {
    assertThatIllegalStateException().isThrownBy(() -> new PersonDto("123456789").fetchIdentType())
        .withMessage("Ukjent ident: \"123456789\"");
  }

  @Test
  @DisplayName("skal gi identtype som bnr når siffer 3 og 4 er et tall høyere enn 20")
  void skalGiIdenttypeBnrNarManedStrengErStorreEnn20() {
    assertThat(new PersonDto("06217412345").fetchIdentType()).isEqualTo("bnr");
  }

  @Test
  @DisplayName("skal gi identtype som NorskIdent når 11 siffer")
  void skalGiIdenttypeNorskIdentNarElleveSiffer() {
    assertThat(new PersonDto("06127412345").fetchIdentType()).isEqualTo("NorskIdent");
  }
}
