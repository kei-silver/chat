package com.chatandpay.ws.chat.dto


import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.UUID

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
}





