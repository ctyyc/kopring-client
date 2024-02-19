package com.example.client.kakao

import com.example.client.common.helper.HttpHelper
import com.example.client.kakao.dto.BlogRequestDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class KakaoApiService(
        private val httpHelper: HttpHelper
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

    fun searchBlog(blogDto: BlogRequestDto): String? {
        val webClient: WebClient = WebClient
                .builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()

        val response = webClient
                .get()
                .uri {
                    it.path("/v2/search/blog")
                        .queryParam("query", blogDto.query)
                        .queryParam("sort", blogDto.sort)
                        .queryParam("page", blogDto.page)
                        .queryParam("size", blogDto.size)
                        .build()
                }
                .header("Authorization", "KakaoAK $restApiKey")
                .retrieve()
                .bodyToMono<String>()

        val result = response.block()

//        val lowQuery: String = blogDto.query.lowercase()
//        val word: WordCount = wordCountRepository.findById(lowQuery).orElse(WordCount(lowQuery))
//        word.cnt++
//
//        wordCountRepository.save(word)

        return result
    }

    fun searchBook(query: String, sort: String?, page: Int?, size: Int?, target: String?): String? {
        val httpHeaders = HttpHeaders()
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        httpHeaders.set("Authorization", "KakaoAK $restApiKey")

        val params = mapOf(
                "query" to query,
                "sort" to sort,
                "page" to page,
                "size" to size,
                "target" to target)

        val response = httpHelper.requestGetResponseString(
                "/v3/search/book",
                httpHeaders,
                params
        )

        return response
    }
}
