package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.entity.GroupChatMessage
import org.bson.types.ObjectId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository

interface GroupChatMessageRepository : MongoRepository<GroupChatMessage, ObjectId> {
}