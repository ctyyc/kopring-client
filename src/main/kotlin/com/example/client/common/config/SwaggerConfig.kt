package com.example.client.common.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(info = Info(title = "Kopring API Test", description = "Kopring API Test...", version = "v1"))
@Configuration
class SwaggerConfig {

}