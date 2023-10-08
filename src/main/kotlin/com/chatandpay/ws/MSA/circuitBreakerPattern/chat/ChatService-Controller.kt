package com.chatandpay.ws.MSA.circuitBreakerPattern.chat

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit


@RestController
class `ChatService-Controller`(
    private val chatMessageService: `ChatService-MessageService`
) {

    @GetMapping("/chat-message")
    fun getChatMessage() {
        repeat(30){
            chatMessageService.getChatMessage()
            TimeUnit.SECONDS.sleep(1)
        }
    }

}