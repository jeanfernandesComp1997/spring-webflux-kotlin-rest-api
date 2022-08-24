package com.example.springwebfluxkotlinrestapi.infra.providers

import com.example.springwebfluxkotlinrestapi.domain.dtos.RickAndMortyFindAllResponse
import com.example.springwebfluxkotlinrestapi.infra.providers.interfaces.RickAndMortyApiProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class RickAndMortyApiProviderImpl(
    webClientBuilder: WebClient.Builder,
    @Value(value = "\${providers.rickAndMortyBaseUri}") private val baseUri: String
) : RickAndMortyApiProvider {

    private val webClient: WebClient

    init {
        webClient = webClientBuilder.baseUrl(baseUri).build()
    }

    override fun findAll(): Mono<RickAndMortyFindAllResponse> {
        return webClient.get()
            .uri("/character")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(RickAndMortyFindAllResponse::class.java)
    }
}