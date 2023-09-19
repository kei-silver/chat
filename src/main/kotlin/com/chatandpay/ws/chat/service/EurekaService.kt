//package com.chatandpay.ws.chat.service
//
//import com.netflix.appinfo.InstanceInfo
//import com.netflix.discovery.EurekaClient
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//
//
//@Service
//class EurekaService(
//    private val discoveryClient: EurekaClient? = null
//) {
//
//    fun serviceUrl() {
//        val instance: InstanceInfo = discoveryClient!!.getNextServerFromEureka("chat", false)
//        println(instance.getHomePageUrl())
//    }
//}
//
