package cz.martinvedra.triagebe2.service.csas

import cz.martinvedra.triagebe2.api.csas.dto.ExchangeRateDto

interface ExchangeRateService {
    fun getExchangeRates(): List<ExchangeRateDto>
}
