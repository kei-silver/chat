package com.chatandpay.ws.chat.entity

import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.chatandpay.ws.utils.toEpochMillis
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "PrivateChatMessage")
data class PrivateChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    val message: String,

    val senderName: String,

    val senderId: Long,

    val receiverId: Long,

    val receiverName: String?,

    val createdAt: Long = LocalDateTime.now().toEpochMillis(),

    val sequenceNumber: Long
){
    companion object {
        @JvmStatic
        fun create(chatMessageDto: ChatMessageDto): PrivateChatMessage {
            return PrivateChatMessage(
                message = chatMessageDto.message,
                senderId = chatMessageDto.senderId,
                senderName = chatMessageDto.senderName,
                receiverId = chatMessageDto.receiverId,
                receiverName = chatMessageDto.receiverName,
                createdAt = chatMessageDto.createdAt,
                sequenceNumber = chatMessageDto.sequenceNumber
            )
        }

        @JvmStatic
        fun createEnterMessage(chatMessageDto: ChatMessageDto): PrivateChatMessage {
            return PrivateChatMessage(
                senderName = chatMessageDto.senderName,
                senderId = chatMessageDto.senderId,
                message = "${chatMessageDto.senderName}님이 입장했습니다.",
                receiverId = chatMessageDto.receiverId,
                receiverName = chatMessageDto.receiverName,
                sequenceNumber =  chatMessageDto.sequenceNumber
            )
        }

    }


}

