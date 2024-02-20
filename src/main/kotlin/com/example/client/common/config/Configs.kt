package com.example.client.common.config

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.util.concurrent.TimeUnit

@Configuration
class Configs {
    @Bean
    fun webClient(): WebClient {
        return WebClient.create("https://dapi.kakao.com")
    }

    @Bean
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }

}