package com.chatandpay.ws.chat.entity

import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime



@Document(collection = "ChatMessage")
data class ChatMessage(
    @Id
    var id: ObjectId? = null,

    // 기존 속성들은 그대로 유지합니다
    var chatRoomId: String,
    val message: String,
    val senderName: String,
    val senderId: Long,
    val createdAt: Long = LocalDateTime.now().toEpochMillis()
) {
    companion object {
        @JvmStatic
        fun create(chatMessageDto: ChatMessageDto): ChatMessage {
            return ChatMessage(
                message = chatMessageDto.message,
                senderId = chatMessageDto.senderId,
                senderName = chatMessageDto.senderName,
                createdAt = chatMessageDto.createdAt,
                chatRoomId = chatMessageDto.chatRoomId
            )
        }

        @JvmStatic
        fun createEnterMessage(chatMessageDto: ChatMessageDto): ChatMessage {
            return ChatMessage(
                senderName = chatMessageDto.senderName,
                senderId = chatMessageDto.senderId,
                message = "${chatMessageDto.senderName}님이 입장했습니다.",
                chatRoomId = chatMessageDto.chatRoomId
            )
        }

    }


}

