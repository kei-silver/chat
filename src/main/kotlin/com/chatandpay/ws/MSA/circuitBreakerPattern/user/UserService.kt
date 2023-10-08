package com.chatandpay.ws.MSA.circuitBreakerPattern.user

import com.chatandpay.ws.chat.entity.ChatUser
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class UserService {

    fun getUserInNoReponse():ChatUser {
        // 50% 확률로 응답이 없는 것을 시뮬레이션
        if (Random.nextBoolean()) {
            // 응답이 없는 경우
            throw RuntimeException("No response available")
        } else {
            // 실제 응답 데이터 반환
            return ChatUser(id = 1, username = "kei")
        }
    }

    fun getUserInSlowResponse():ChatUser {
        Thread.sleep(5000)
        return ChatUser(id = 1, username = "kei")
    }
}