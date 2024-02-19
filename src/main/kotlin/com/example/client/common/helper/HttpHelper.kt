package com.example.client.common.helper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class HttpHelper(
        @Autowired
        private val webClient: WebClient
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

    fun requestGetResponseString(path: String, headers: HttpHeaders, params: Map<String, Any?>?): String? {
        val requestBuilder = webClient
                .get()
                .uri { uriBuilder ->
                    uriBuilder.path(path)
                    params?.let {
                        it.forEach { (key, value) ->
                            if(value != null) uriBuilder.queryParam(key, value)
                        }
                    }
                    uriBuilder.build()
                }
                .headers { httpHeaders -> httpHeaders.addAll(headers) }

        return requestBuilder.retrieve().bodyToMono<String>().block()
    }

}