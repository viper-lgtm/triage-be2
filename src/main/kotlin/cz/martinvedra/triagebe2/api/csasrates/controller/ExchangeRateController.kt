package cz.martinvedra.triagebe2.api.csasrates.controller

import cz.martinvedra.triagebe2.api.csasrates.dto.ExchangeRateDto

interface ExchangeRateController {
    fun getExchangeRates(): List<ExchangeRateDto>
}
