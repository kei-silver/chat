package com.chatandpay.ws.chat.consumer

import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.chatandpay.ws.chat.service.ChatMessageService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class MongoDBListener (
    var chatMessageService: ChatMessageService
    ){
        @KafkaListener(topics = ["chat-topic"], groupId = "chat-broadcast")
        fun listenDB(@Payload chatMessage: ChatMessageDto) {
            try {
                println("Received Kafka message for mongodb: $chatMessage")
                chatMessageService.saveChatMessage(chatMessage)
            } catch (e: Exception) {
                throw e
            }
        }
}