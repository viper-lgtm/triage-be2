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
        return restClient.getRates()
    }

    override fun publishRates(): Int {
        val rates = getExchangeRates()
        rates.forEach { rate ->
            val key = rate.shortName ?: "n/a"
            kafkaProducerService.sendRate(key, rate)
        }
        return rates.size
    }
}
