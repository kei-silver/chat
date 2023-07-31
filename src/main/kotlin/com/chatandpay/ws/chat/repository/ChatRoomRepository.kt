package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.entity.ChatRoom
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


interface ChatRoomRepository : MongoRepository<ChatRoom, String> {
}



