package com.chatandpay.ws.configuration


import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // /webjars/** 경로로 시작하는 요청은 웹 자원을 제공하기 위해 사용됩니다.
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS를 활성화
            .allowedOrigins("*") // 모든 출처를 허용
            .allowedMethods("*") // 모든 HTTP 메서드를 허용
            .allowedHeaders("*") // 모든 헤더를 허용
            .exposedHeaders("*") // 모든 노출 헤더를 허용
            .allowCredentials(false) // 자격 증명(인증) 허용하지 않음
            .maxAge(3600) // Preflight 요청의 유효 기간 설정 (초 단위)
    }
}
