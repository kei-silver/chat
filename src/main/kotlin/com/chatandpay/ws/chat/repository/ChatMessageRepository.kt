package com.chatandpay.ws.chat.repository

import com.chatandpay.ws.chat.entity.PrivateChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.EntityManager
import javax.persistence.TypedQuery


interface PrivateChatMessageRepository : JpaRepository<PrivateChatMessage, Long> {

//    fun findBySenderId(senderId: Long): List<PrivateChatMessage>

}

class PrivateChatMessageRepositoryImpl(private val entityManager: EntityManager){

    // chatRoomId에 해당하는 데이터 중 가장 큰 시퀀스 넘버 조회
    fun findLatestSequenceNumberByChatRoomId(chatRoomId: Long?): Long? {
        println(chatRoomId);
        val query: TypedQuery<Long?> = entityManager.createQuery(
            "SELECT MAX(cm.sequenceNumber) FROM PrivateChatMessage cm " +
                    "WHERE cm.chatRoomId = :chatRoomId", Long::class.javaObjectType
        )
        query.setParameter("chatRoomId", chatRoomId)
        return query.singleResult
    }
}