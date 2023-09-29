package com.chatandpay.ws.chat.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType


@Document(collection = "ChatUser")
data class ChatUser(

    @Id
    @Field("_id", targetType = FieldType.OBJECT_ID)
    var id: Long? = null,

    var username: String,

    var email: String? = null,

    var createdAt: Long? = System.currentTimeMillis()
)