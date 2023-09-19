//package com.chatandpay.ws.chat.service
//
//import org.springframework.data.redis.connection.Message
//import org.springframework.data.redis.connection.MessageListener
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.stereotype.Service
//
//
//@Service
//class RedisMessageSubscriber: MessageListener{
//
//    private val redisTemplate: RedisTemplate<*, *>? = null
//
//    override fun onMessage(message: Message, pattern: ByteArray?) {
//        messageList.add(message.toString())
//        System.out.println("Received Redis message: " + message.toString())
//        redisTemplate?.convertAndSend("/topic/group", message)
//    }
//
//    companion object {
//        var messageList: MutableList<String> = ArrayList()
//    }
//}