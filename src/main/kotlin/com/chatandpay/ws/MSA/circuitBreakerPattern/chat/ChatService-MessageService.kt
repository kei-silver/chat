package com.chatandpay.ws.MSA.circuitBreakerPattern.chat


import com.chatandpay.ws.chat.entity.ChatMessage
import com.chatandpay.ws.MSA.circuitBreakerPattern.user.UserService
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class `ChatService-MessageService` (
    private val logger: Logger = LoggerFactory.getLogger(`ChatService-MessageService`::class.java),
    private val userServer: UserService
    ){


    @CircuitBreaker(name = "ChatMessageService", fallbackMethod = "fallback")
    fun getChatMessage(): ChatMessage? {
        val user = userServer.getUserInNoReponse()

        if(user != null){
            logger.info("호출 성공")
            return ChatMessage(senderId = 1, senderName = "kei", message = "message", chatRoomId = "1")
        }else{
            logger.error("호출 실패")
            return null
        }
    }

    fun fallback(ex: Throwable):ChatMessage? {
        logger.error("응답 실패")
        return null
    }

    fun fallback(ex: CallNotPermittedException):ChatMessage? {
        logger.error("서킷 브레이커 open")
        return null
    }


}