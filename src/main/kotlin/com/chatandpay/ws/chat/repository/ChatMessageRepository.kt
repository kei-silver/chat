package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.entity.ChatMessage
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository



interface ChatMessageRepository : MongoRepository<ChatMessage, ObjectId> {
}

