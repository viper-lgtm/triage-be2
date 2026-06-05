package cz.martinvedra.triagebe2.service.csas

import cz.martinvedra.triagebe2.api.csas.dto.ExchangeRateDto
import cz.martinvedra.triagebe2.client.ExchangeRateClient
import org.springframework.stereotype.Service

@Service
class ExchangeRateServiceImpl(
    private val restClient: ExchangeRateClient
) : ExchangeRateService {
    override fun getExchangeRates(): List<ExchangeRateDto> {
        return restClient.getRates()
    }
}
