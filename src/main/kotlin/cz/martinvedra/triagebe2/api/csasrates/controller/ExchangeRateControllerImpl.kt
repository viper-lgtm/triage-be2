package cz.martinvedra.triagebe2.api.csasrates.controller

import cz.martinvedra.triagebe2.api.csasrates.dto.ExchangeRateDto
import cz.martinvedra.triagebe2.service.csasrates.ExchangeRateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/be2")
class ExchangeRateControllerImpl(
    private val exchangeRateService: ExchangeRateService
) : ExchangeRateController {


    @GetMapping
    override fun getExchangeRates(): List<ExchangeRateDto> {
        return exchangeRateService.getExchangeRates()
    }

    @GetMapping("/publish")
    fun publish(): String {
        val count = exchangeRateService.publishRates()
        return "published=$count"
    }
}
