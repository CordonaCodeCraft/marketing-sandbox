package tech.cordona.marketingsandbox

import com.github.javafaker.Faker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApplicationConfig {
	@Bean
	fun faker() = Faker()
}