package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.entity.UserChatRoom
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


interface UserChatRoomRepository : MongoRepository<UserChatRoom, ObjectId> {
}






