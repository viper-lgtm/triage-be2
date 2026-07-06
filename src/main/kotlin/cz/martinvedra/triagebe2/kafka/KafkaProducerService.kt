package cz.martinvedra.triagebe2.kafka

import cz.martinvedra.triagebe2.api.csasrates.dto.ExchangeRateDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import tools.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val mapper: ObjectMapper,
    @Value("\${app.kafka.topic:exchange-rates}")
    private val topic: String
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendRate(key: String, rate: ExchangeRateDto) {
        log.debug("Publishing rate key={} to topic={}", key, topic)
        val json = mapper.writeValueAsString(rate)
        kafkaTemplate.send(topic, key, json)
    }
}
