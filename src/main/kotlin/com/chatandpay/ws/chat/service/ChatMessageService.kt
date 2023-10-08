package com.chatandpay.ws.chat.service


import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.chatandpay.ws.chat.entity.ChatMessage
import com.chatandpay.ws.chat.repository.ChatMessageRepository
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.apache.commons.logging.Log
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service


@Service
class ChatMessageService (
    private val logger: Logger = LoggerFactory.getLogger(ChatMessageService::class.java),
    private val chatMessageRepository: ChatMessageRepository

    ){


    fun saveChatMessage(chatMessageDto: ChatMessageDto) {
        val chatMessage = ChatMessage.create(chatMessageDto)
        chatMessageRepository.save(chatMessage)
    }

    fun getChatMessagesByRoomId(chatMessageDto: ChatMessageDto):ChatMessage {

//        // 채팅방의 모든 채팅 기록 조회 -********** -> 시퀀스 번호 별로 마지막 10개 뽑아서 보여줘야함
//        val chatMessages = privateChatMessageRepository.findBySenderId(chatMessageDto.senderId)
//        // 나중에 현재 유저의 아이디 값으로 변경할 것
//
//        println(chatMessages);
//        if (chatMessages.isNotEmpty()) {
//            return chatMessages.map {chatMessage -> PrivateChatMessage.create(chatMessageDto)
//            }
//        }

//        // 접속했을때 시퀀스번호를 조회하고 할당함
//        val sequenceNumber = privateChatMessageRepository.findLatestSequenceNumberByChatRoomId(chatMessageDto.chatRoomId);
//        chatMessageDto.sequenceNumber = sequenceNumber?.plus(1)
        // 채팅방 기록이 없다면 최초 입장으로 파악 -> 입장했습니다 메시지 반환

//        val chatMessage = PrivateChatMessage.create(chatMessageDto)
//        privateChatMessageRepository.save(chatMessage)


        return ChatMessage.createEnterMessage(chatMessageDto)

    }


}