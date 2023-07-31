package com.chatandpay.ws.chat.entity

import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.*

@Document(collection = "UserChatRoom")
data class UserChatRoom(
    @Id
    var id: ObjectId? = null,

    // 기존 속성들은 그대로 유지합니다
    val chatUserId: ObjectId?,
    val chatRoomId: ObjectId?,
    val createdAt: Long = LocalDateTime.now().toEpochMillis()
) {

    companion object {
        @JvmStatic
        fun create(chatRoomId: ObjectId?, chatUserId: ObjectId?): UserChatRoom {
            return UserChatRoom(chatRoomId = chatRoomId, chatUserId = chatUserId)
        }
    }

}
