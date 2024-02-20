package com.example.client.kakao.dto

import com.example.client.common.annotation.ValidEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BlogRequestDto(
        @field:NotBlank(message = "query parameter required")
        @Schema(description = "검색어", nullable = false, example = "코틀린")
        val query: String?,

        @field:NotBlank(message = "sort parameter required")
        @field:ValidEnum(enumClass = EnumSort::class, message = "sort parameter one of ACCURACY and RECENCY")
        @Schema(description = "정렬순서", example = "ACCURACY")
        val sort: String?,

        @field:NotNull(message = "page parameter required")
        @field:Max(50, message = "page is more than max")
        @field:Min(1, message = "page is less than min")
        @Schema(description = "페이지 번호", example = "1")
        val page: Int?,

        @field:NotNull(message = "size parameter required")
        @Schema(description = "페이지 크기", example = "10")
        val size: Int?
) {
    private enum class EnumSort {
        ACCURACY,
        RECENCY
    }
}
