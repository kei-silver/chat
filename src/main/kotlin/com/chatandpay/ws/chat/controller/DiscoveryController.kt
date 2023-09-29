//package com.chatandpay.ws.chat.controller
//
//import com.chatandpay.ws.chat.service.DiscoveryService
//import org.springframework.cloud.client.ServiceInstance
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RestController
//import java.util.concurrent.TimeUnit
//
//@RestController
//class DiscoveryController(
//    private val discoveryService: DiscoveryService
//) {
//
//    @GetMapping("/discover")
//    fun getChatMessage() {
//        return discoveryService.getAllServiceUrl();
//    }
//}