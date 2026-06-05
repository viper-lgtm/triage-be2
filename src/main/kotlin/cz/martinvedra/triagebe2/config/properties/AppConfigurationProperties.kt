package cz.martinvedra.triagebe2.config.properties

import cz.martinvedra.triagebe2.config.properties.AppConfigurationProperties.Companion.PREFIX_NAME
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(PREFIX_NAME)
class AppConfigurationProperties(
    val enabled: Boolean = true,
    val csas: CsasProperties
) {
    companion object {
        const val PREFIX_NAME = "app"
    }

    class CsasProperties(
        val baseUrl: String
    )
}