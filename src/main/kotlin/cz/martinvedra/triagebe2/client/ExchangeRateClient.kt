package cz.martinvedra.triagebe2.client

import cz.martinvedra.triagebe2.api.csas.dto.ExchangeRateDto
import cz.martinvedra.triagebe2.config.properties.AppConfigurationProperties
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.util.Collections.emptyList

@Component
class ExchangeRateClient(
    private val properties: AppConfigurationProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val restClient: RestClient by lazy {
        log.info("Initializing RestClient for CSAS API")
        RestClient.builder()
            .baseUrl(properties.csas.baseUrl)
            .build()
    }

    @RateLimiter(name = "csasLimit")
    fun getRates(): List<ExchangeRateDto> {
        log.debug("Fetching exchange rates from CSAS API")
        return try {
            restClient.get()
                .retrieve()
                .body<Array<ExchangeRateDto>>()
                ?.toList() ?: emptyList()
        } catch (e: Exception) {
            log.error("Failed to fetch exchange rates: ${e.message}", e)
            emptyList()
        }

    }
}