package com.example.client.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Configs {

    @Bean
    fun webClient(): WebClient {
        return WebClient.create("https://dapi.kakao.com")
    }
}