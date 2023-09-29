//package com.chatandpay.ws.chat.service
//
//import com.netflix.appinfo.InstanceInfo
//import com.netflix.discovery.EurekaClient
//import org.springframework.cloud.client.discovery.DiscoveryClient
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.cloud.client.ServiceInstance
//import org.springframework.stereotype.Service
//import java.net.URI
//
//
//@Service
//class DiscoveryService(
//    private val discoveryClient: DiscoveryClient? = null,
//    private val eurekaClient: EurekaClient? = null
//) {
//
//    fun getServiceUrl() {
//        val instance: InstanceInfo = eurekaClient!!.getNextServerFromEureka("chat", false)
//        println(instance.getHomePageUrl())
//    }
//
//    fun getAllServiceUrl(){
//        val instances: List<ServiceInstance> = discoveryClient!!.getInstances("chat") as List<ServiceInstance>
//
//        for (instance in instances) {
//            println(instance)
//            println(instance.uri)
//        }
//    }
//}
//
