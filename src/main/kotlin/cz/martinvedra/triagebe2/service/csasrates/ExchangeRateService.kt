package cz.martinvedra.triagebe2.service.csasrates

import cz.martinvedra.triagebe2.api.csasrates.dto.ExchangeRateDto

interface ExchangeRateService {
    fun getExchangeRates(): List<ExchangeRateDto>
}
