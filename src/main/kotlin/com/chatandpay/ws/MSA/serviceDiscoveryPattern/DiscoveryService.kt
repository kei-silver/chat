package com.chatandpay.ws.MSA.serviceDiscoveryPattern

import com.netflix.appinfo.InstanceInfo
import com.netflix.discovery.EurekaClient
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.ServiceInstance
import org.springframework.stereotype.Service
import java.net.URI


@Service
class DiscoveryService(
    private val discoveryClient: DiscoveryClient? = null,
    private val eurekaClient: EurekaClient? = null
) {

    fun getChatService():String? {
        val instance: InstanceInfo = eurekaClient!!.getNextServerFromEureka("chat", false)
        println("discovered server : "+ instance.getHomePageUrl())
        return instance.getHomePageUrl()
    }

    fun getAllChatServices(): List<URI>? {
        val instances: List<ServiceInstance> = discoveryClient!!.getInstances("chat") as List<ServiceInstance>
        val discoveredServerlist: MutableList<URI> = mutableListOf() // 빈 목록을 만듭니다.

        for (instance in instances) {
            println(instance.uri)
            discoveredServerlist.add(instance.uri) // URI를 목록에 추가합니다.
        }

        // 모든 인스턴스 URI가 목록에 추가되었다면, 목록을 반환합니다.
        return if (discoveredServerlist.isNotEmpty()) {
            discoveredServerlist
        } else {
            null
        }
    }
}

