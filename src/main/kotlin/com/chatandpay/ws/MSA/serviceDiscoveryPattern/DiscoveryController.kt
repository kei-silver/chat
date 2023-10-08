package com.chatandpay.ws.chat.controller

import com.chatandpay.ws.MSA.serviceDiscoveryPattern.DiscoveryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DiscoveryController(
    private val discoveryService: DiscoveryService
) {

    @GetMapping("/chat-service")
    fun getChatService():String? {
        return discoveryService.getChatService()
    }
}