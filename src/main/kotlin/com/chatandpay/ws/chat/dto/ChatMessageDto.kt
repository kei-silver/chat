package com.chatandpay.ws.chat.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ChatMessageDto @JsonCreator constructor(
    @JsonProperty("type") val type: Type,
    @JsonProperty("chatRoomId") val chatRoomId: String,
    @JsonProperty("senderName") val senderName: String,
    @JsonProperty("senderId") val senderId: Long,
    @JsonProperty("message") val message: String,
    @JsonProperty("createdAt") val createdAt: Long
) {
    enum class Type {
        ENTER, COMMENT
    }

    companion object {

        @JvmStatic
        fun createEnterMessage(chatMessageDto: ChatMessageDto): ChatMessageDto {

            return ChatMessageDto(
                senderName = chatMessageDto.senderName,
                senderId = chatMessageDto.senderId,
                message = "${chatMessageDto.senderName}님이 입장했습니다.",
                chatRoomId = chatMessageDto.chatRoomId,
                type = Type.ENTER,
                createdAt = chatMessageDto.createdAt
            )
        }
    }
}
