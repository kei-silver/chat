package com.chatandpay.ws.chat.entity

import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime


@Document(collection = "UserChatRoom")
data class UserChatRoom(
    @Id
    var id: ObjectId? = null,

    // 기존 속성들은 그대로 유지합니다
    val chatUserId: String?,
    val chatRoomId: String?,
    val createdAt: Long = LocalDateTime.now().toEpochMillis()
) {

    companion object {
        @JvmStatic
        fun create(chatRoomId: String?, chatUserId: String?): UserChatRoom {
            return UserChatRoom(chatRoomId = chatRoomId, chatUserId = chatUserId)
        }
    }

}
