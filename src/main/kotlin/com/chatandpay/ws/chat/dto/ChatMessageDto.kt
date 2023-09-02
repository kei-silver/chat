package com.chatandpay.ws.chat.dto



data class ChatMessageDto(
    val type: Type,
    val chatRoomId: String,
    val senderName: String,
    val senderId: String,
    val message: String,
    val createdAt: Long

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





