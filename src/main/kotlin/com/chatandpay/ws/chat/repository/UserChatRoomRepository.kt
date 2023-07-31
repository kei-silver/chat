package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.dto.UserChatRoomDto
import com.chatandpay.ws.chat.entity.UserChatRoom
import org.bson.types.ObjectId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement


interface UserChatRoomRepository : MongoRepository<UserChatRoom, ObjectId> {
}






