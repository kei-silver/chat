package com.chatandpay.ws.chat.entity


import com.chatandpay.ws.utils.toEpochMillis
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.time.LocalDateTime

@Document(collection = "ChatRoom")
data class ChatRoom(
    @Id
    @Field("_id", targetType = FieldType.OBJECT_ID)
    var id: String? = null,

    var name: String,

    var type: Type,

    var createdAt: Long = LocalDateTime.now().toEpochMillis()
) {
    enum class Type {
        GROUP, PRIVATE
    }

    companion object {
        @JvmStatic
        fun create(name: String, type: Type): ChatRoom {
            return ChatRoom(name = name, type = type)
        }
    }
}
