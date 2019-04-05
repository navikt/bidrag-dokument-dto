package no.nav.bidrag.dokument.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AktorTest {

  @Test
  @DisplayName("skal kunne gi hvilken som helst verdi til ident og identtype for en person")
  void skalGiVerdiTilIdenOgIdenttypeForEnPerson() {
    PersonDto personDto = new PersonDto();
    personDto.setIdentType("type");

    assertAll(
        () -> assertThat(personDto.getIdentType()).isEqualTo("type"),
        () -> assertThat(personDto.getAktorType()).isEqualTo("person")
    );
  }

  @Test
  @DisplayName("skal feile når personident er blank")
  void skalFeileNarIdentErBlank() {
    assertThatIllegalStateException().isThrownBy(() -> new PersonDto("").fetchIdentType())
        .withMessage("Ukjent ident () for person");
  }

  @Test
  @DisplayName("skal feile når personident ikke er tall")
  void skalFeileNarIdentIkkeErTall() {
    assertThatIllegalStateException().isThrownBy(() -> new PersonDto("abcdefgh").fetchIdentType())
        .withMessage("Ukjent ident (abcdefgh) for person");
  }

  @Test
  @DisplayName("skal feile når personident ikke har 11 siffer")
  void skalFeileNarIdentIkkeHarElleveSiffer() {
    assertAll(
        () -> assertThatIllegalStateException().isThrownBy(() -> new PersonDto("1234567890").fetchIdentType())
            .withMessage("Ukjent ident (1234567890) for person"),
        () -> assertThat(new PersonDto("12345678901").fetchIdentType()).isNotNull(),
        () -> assertThatIllegalStateException().isThrownBy(() -> new PersonDto("123456789012").fetchIdentType())
            .withMessage("Ukjent ident (123456789012) for person")
    );
  }

  @Test
  @DisplayName("skal gi personidenttype som bnr når siffer 3 og 4 er et tall høyere enn 20")
  void skalGiIdenttypeBnrNarManedStrengErStorreEnn20() {
    assertThat(new PersonDto("06217412345").fetchIdentType()).isEqualTo("bnr");
  }

  @Test
  @DisplayName("skal gi personidenttype som NorskIdent når 11 siffer")
  void skalGiIdenttypeNorskIdentNarElleveSiffer() {
    assertThat(new PersonDto("06127412345").fetchIdentType()).isEqualTo("NorskIdent");
  }

  @Test
  @DisplayName("skal kunne gi hvilken som helst verdi til ident og identtype for en organisasjon")
  void skalGiVerdiTilIdenOgIdenttypeForEnOrganisasjon() {
    OrganisasjonDto organisasjonDto = new OrganisasjonDto();
    organisasjonDto.setIdentType("type");

    assertAll(
        () -> assertThat(organisasjonDto.getIdentType()).isEqualTo("type"),
        () -> assertThat(organisasjonDto.getAktorType()).isEqualTo("organisasjon")
    );
  }

  @Test
  @DisplayName("skal feile når ident er blank for organisasjon")
  void skalFeileNarIdentErBlankForOrganisasjon() {
    assertThatIllegalStateException().isThrownBy(() -> new OrganisasjonDto("").fetchIdentType())
        .withMessage("Ukjent ident () for organisasjon");
  }

  @Test
  @DisplayName("skal feile når ident ikke er tall for organisasjon")
  void skalFeileNarIdentIkkeErTallForOrgnisasjon() {
    assertThatIllegalStateException().isThrownBy(() -> new OrganisasjonDto("abcdefgh").fetchIdentType())
        .withMessage("Ukjent ident (abcdefgh) for organisasjon");
  }

  @Test
  @DisplayName("skal feile når organisasjonsident ikke har 9 siffer")
  void skalFeileNarIdentIkkeHarNiSiffer() {
    assertAll(
        () -> assertThatIllegalStateException().isThrownBy(() -> new OrganisasjonDto("12345678").fetchIdentType())
            .withMessage("Ukjent ident (12345678) for organisasjon"),
        () -> assertThat(new OrganisasjonDto("123456789").fetchIdentType()).isNotNull(),
        () -> assertThatIllegalStateException().isThrownBy(() -> new OrganisasjonDto("1234567890").fetchIdentType())
            .withMessage("Ukjent ident (1234567890) for organisasjon")
    );
  }

  @Test
  @DisplayName("skal gi organisasjonidenttype som orgnr når 9 siffer")
  void skalGiIdenttypeOrgnrNarNiSiffer() {
    assertThat(new OrganisasjonDto("123456789").fetchIdentType()).isEqualTo("orgnr");
  }

  @Test
  @DisplayName("skal si om aktører er en person eller ei")
  void skalSiOmAktorenErPerson() {
    assertAll(
        () -> assertThat(new OrganisasjonDto().erPerson()).isFalse(),
        () -> assertThat(new PersonDto().erPerson()).isTrue()
    );
  }
}
