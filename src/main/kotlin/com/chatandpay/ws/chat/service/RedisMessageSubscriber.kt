package com.chatandpay.ws.chat.service

import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service


@Service
class RedisMessageSubscriber(
    var template: SimpMessagingTemplate
):MessageListener{

    private val objectMapper = ObjectMapper()
    override fun onMessage(message: Message, pattern: ByteArray?) {
        System.out.println("Received Redis message: " + message.toString())
        try {
            // JSON 데이터를 ChatMessageDto 타입으로 변환
            val messagePayload = String(message.body)
            val chatMessage:ChatMessageDto = objectMapper.readValue(messagePayload, ChatMessageDto::class.java)
            // chatMessage를 사용하여 원하는 작업 수행
            template.convertAndSend("/topic/group/room/${chatMessage.chatRoomId}", chatMessage)
            System.out.println("Sent Redis message")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        var messageList: MutableList<String> = ArrayList()
    }
}