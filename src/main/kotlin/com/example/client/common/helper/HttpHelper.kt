package com.example.client.common.helper

import com.example.client.common.exception.BizException
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class HttpHelper(
        private val webClient: WebClient,
        private val okHttpClient: OkHttpClient
) {
    companion object {
        val JSON: MediaType = "application/json".toMediaType()
    }

    fun requestGetResponseString(path: String, headers: HttpHeaders, params: Map<String, String?>): String? {
        val requestBuilder = webClient
                .get()
                .uri {
                    it.path(path)
                    params.forEach { (key, value) -> if(value != null) it.queryParam(key, value) }
                    it.build()
                }
                .headers { httpHeaders -> httpHeaders.addAll(headers) }

        return requestBuilder.retrieve().bodyToMono<String>().block()
    }

    fun postParamsHttpRequest(url: String, params: MutableMap<String, String>): String {
        val formBody = setFormBody(params)
        val request: Request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()

        okHttpClient.newCall(request).execute().use { response ->
            if(response.isSuccessful) {
                return response.body?.string() ?: ""
            } else {
                throw BizException("API 요청 중 오류 발생")
            }
        }
    }

    fun postJsonHttpRequest(url: String, json: String): String {
        val body: RequestBody = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        okHttpClient.newCall(request).execute().use { response ->
            if(response.isSuccessful) {
                return response.body?.string() ?: ""
            } else {
                throw BizException("API 요청 중 오류 발생")
            }
        }
    }

    fun postJsonHttpRequest(url: String, json: String, header: MutableMap<String, String>): String {
        val headers: Headers = setHeaders(header)
        val body: RequestBody = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build()

        okHttpClient.newCall(request).execute().use { response ->
            if(response.isSuccessful) {
                return response.body?.string() ?: ""
            } else {
                throw BizException("API 요청 중 오류 발생")
            }
        }
    }

    fun setFormBody(params: Map<String, String>): FormBody {
        val formBody = FormBody.Builder()

        params.forEach { (key, value) ->
            formBody.add(key, value)
        }

        return formBody.build()
    }

    fun setHeaders(params: Map<String, String>): Headers {
        val headersBuilder = Headers.Builder()

        params.forEach { (key, value) ->
            headersBuilder.add(key, value)
        }

        return headersBuilder.build()
    }

}