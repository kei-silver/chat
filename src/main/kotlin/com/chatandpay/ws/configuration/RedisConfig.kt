//package com.chatandpay.ws.configuration
//
//import com.chatandpay.ws.chat.dto.ChatMessageDto
//import com.chatandpay.ws.chat.service.RedisMessageSubscriber
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.connection.RedisConnectionFactory
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.listener.ChannelTopic
//import org.springframework.data.redis.listener.RedisMessageListenerContainer
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
//import org.springframework.data.redis.serializer.StringRedisSerializer
//
//
//@Configuration
//class RedisConfig {
//
//    @Bean
//    fun redisConnectionFactory(): RedisConnectionFactory {
//        return LettuceConnectionFactory()
//    }
//
//
//    @Bean
//    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, ChatMessageDto> {
//        val template = RedisTemplate<String, ChatMessageDto>()
//        template.setConnectionFactory(connectionFactory)
//        template.keySerializer = StringRedisSerializer()
//        template.valueSerializer = Jackson2JsonRedisSerializer(ChatMessageDto::class.java)
//        return template
//    }
//
//
////    @Bean
////    fun redisMessageListener(): MessageListenerAdapter? {
////        return MessageListenerAdapter(RedisMessageSubscriber())
////    }
//
//    @Bean
//    fun redisContainer(): RedisMessageListenerContainer? {
//        val container = RedisMessageListenerContainer()
//        container.setConnectionFactory(redisConnectionFactory())
////        container.addMessageListener(redisMessageListener()!!, topic())
//        return container
//    }
//
//
//
//    @Bean
//    fun topic(): ChannelTopic {
//        return ChannelTopic("/topic/group")
//    }
//
//
//}
//
//
