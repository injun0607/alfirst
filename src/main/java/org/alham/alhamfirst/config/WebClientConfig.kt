package org.alham.alhamfirst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebClientConfig {

    val BASE_URL = "http://localhost:9080"; //파이썬 서버 URL

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder):WebClient {
        return webClientBuilder.baseUrl(BASE_URL) //파이썬 서버 URL
        .build();
    }

    @Bean
    fun corsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true)
            }
        }
    }

}
