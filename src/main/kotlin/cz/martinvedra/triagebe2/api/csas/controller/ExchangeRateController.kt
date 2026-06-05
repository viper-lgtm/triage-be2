package cz.martinvedra.triagebe2.api.csas.controller

import cz.martinvedra.triagebe2.api.csas.dto.ExchangeRateDto

interface ExchangeRateController {
    fun getExchangeRates(): List<ExchangeRateDto>
}
