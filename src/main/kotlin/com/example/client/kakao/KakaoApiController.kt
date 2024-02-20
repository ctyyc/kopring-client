package com.example.client.kakao

import com.example.client.common.exception.InvalidInputException
import com.example.client.kakao.dto.BlogRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Kakao API", description = "Kakao API...")
@RequestMapping("/api/v1/kakao")
@RestController
class KakaoApiController(
    val kakaoApiService: KakaoApiService
) {
    @Operation(summary = "Blog 게시글 목록 조회",
            description = "Blog 게시글 목록을 조회합니다.",
            parameters = [
                Parameter(name = "query", `in` = ParameterIn.QUERY),
                Parameter(name = "sort", `in` = ParameterIn.QUERY),
                Parameter(name = "page", `in` = ParameterIn.QUERY),
                Parameter(name = "size", `in` = ParameterIn.QUERY)
            ]
    )
    @GetMapping("/blog")
    fun searchBlog(@Valid @Parameter(hidden = true) blogDto: BlogRequestDto): ResponseEntity<String>? {
        val result: String? = kakaoApiService.searchBlog(blogDto)

        return ResponseEntity.ok(result)
    }

    @Operation(summary = "책 목록 조회", description = "책 목록을 조회합니다.")
    @GetMapping("/book")
    fun searchBook(query: String, sort: String?, page: Int?, size: Int?, target: String?): ResponseEntity<String>? {
        if(query.isBlank()) {
            throw InvalidInputException("query is not blank")
        }
        val result: String? = kakaoApiService.searchBook(query, sort, page, size, target)

        return ResponseEntity.ok(result)
    }
}