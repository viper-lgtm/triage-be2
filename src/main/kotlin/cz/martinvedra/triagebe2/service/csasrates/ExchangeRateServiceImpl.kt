package cz.martinvedra.triagebe2.service.csasrates

import cz.martinvedra.triagebe2.api.csasrates.dto.ExchangeRateDto
import cz.martinvedra.triagebe2.client.ExchangeRateClient
import cz.martinvedra.triagebe2.kafka.KafkaProducerService
import org.springframework.stereotype.Service

@Service
class ExchangeRateServiceImpl(
    private val restClient: ExchangeRateClient,
    private val kafkaProducerService: KafkaProducerService
) : ExchangeRateService {

    override fun getExchangeRates(): List<ExchangeRateDto> {
        val rates = restClient.getRates()
        rates.forEach { rate ->
            val key = rate.shortName ?: "n/a"
            kafkaProducerService.sendRate(key, rate)
        }
        return rates
    }

    override fun publishRates(): Int {
        val rates = getExchangeRates()
        return rates.size
    }
}
