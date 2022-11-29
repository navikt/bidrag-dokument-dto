package no.nav.bidrag.dokument.dto

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BidragDokumentDtoTestApplication

fun main(args: Array<String>) {
    val app = SpringApplication(BidragDokumentDtoTestApplication::class.java)
    app.setAdditionalProfiles(*args)
    app.run(*args)
}