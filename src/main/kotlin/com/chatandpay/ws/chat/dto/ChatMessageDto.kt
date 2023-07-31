package com.chatandpay.ws.chat.dto


import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.UUID

data class ChatMessageDto(
    val type: Type,
    val chatRoomId: ObjectId,
    val senderName: String,
    val senderId: ObjectId,
    val receiverId:  ObjectId,
    val receiverName: String?,
    val message: String,
    val createdAt: Long = LocalDateTime.now().toEpochMillis()

) {
    enum class Type {
        ENTER, COMMENT
    }
}





