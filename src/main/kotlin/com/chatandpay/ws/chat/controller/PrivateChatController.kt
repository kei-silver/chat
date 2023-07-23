package com.chatandpay.ws.chat.controller

import com.chatandpay.ws.chat.dto.ChatMessageDto
import com.chatandpay.ws.chat.entity.PrivateChatMessage
import com.chatandpay.ws.chat.service.ChatMessageService

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller


@Controller
class PrivateChatController(
    private val chatMessageService: ChatMessageService
) {

    @MessageMapping("/pub/chat/room/{roomId}/enter")
    @SendTo("/sub/chat/room/{roomId}")
    fun enter(@DestinationVariable roomId: String,  chatMessageDto: ChatMessageDto): PrivateChatMessage {

        println("접속");
        println(chatMessageDto);
//        chatMessageService.savePrivateChatMessage(chatMessageDto);
        return chatMessageService.getChatMessagesByRoomId(chatMessageDto);

    }


    @MessageMapping("/pub/chat/room/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    fun message(@DestinationVariable roomId: String,  chatMessageDto: ChatMessageDto): PrivateChatMessage {

        // 🔴 메시지 저장 - 보통 이부분은 비동기적으로 처리되지 않을까? 유저가 입력한 메시지를 보여주는게 우선이고 저장이 후순위일 것 같다.
        chatMessageService.savePrivateChatMessage(chatMessageDto);
        return chatMessageService.getChatMessage(chatMessageDto);
    }

}