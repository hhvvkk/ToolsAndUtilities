package com.checkdate.helper.automatichelper.config

import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContexts
import org.apache.http.ssl.TrustStrategy
import org.springframework.context.annotation.Bean
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext


@Component
class RestTeamplateConfig {
/*

    open fun restTemplateTw(): RestTemplate {
        return RestTemplate()
    }
*/

    @Bean
    fun restTemplate(): RestTemplate? {
        val acceptingTrustStrategy =
            TrustStrategy { chain: Array<X509Certificate?>?, authType: String? -> true }
        val sslContext: SSLContext = SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build()
        val csf = SSLConnectionSocketFactory(sslContext)
        val httpClient = HttpClients.custom()
            .setSSLSocketFactory(csf)
            .build()
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.httpClient = httpClient
        return RestTemplate(requestFactory)
    }
/*
    @Bean
    @Throws(KeyStoreException::class, NoSuchAlgorithmException::class, KeyManagementException::class)
    open fun restTemplate(): RestTemplate? {
        val acceptingTrustStrategy = Neo4jProperties.Security.TrustStrategy.TRUST_ALL_CERTIFICATES
        val sslContext: SSLContext = SSLContexts
            .custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build()
        val csf = SSLConnectionSocketFactory(sslContext)
        val httpClient = HttpClients.custom()
            .setSSLSocketFactory(csf)
            .build()
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.httpClient = httpClient
        return RestTemplate(requestFactory)
    }*/
}