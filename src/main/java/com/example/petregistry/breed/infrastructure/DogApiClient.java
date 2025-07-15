package com.example.petregistry.breed.infrastructure;

import com.example.petregistry.breed.application.BreedApi;
import com.example.petregistry.breed.domain.BreedNotFoundException;
import com.example.petregistry.breed.domain.DogApiBreedDto;
import com.example.petregistry.breed.domain.Breed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Slf4j
@Component
public class DogApiClient implements BreedApi {

    private final WebClient webClient;

    @Value("${dogapi.key}")
    private String apiKey;

    @Value("${dogapi.path}")
    private String apiPath;

    public DogApiClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @Override
    @Cacheable("breeds")
    public Breed fetchById(String breedId) {
        log.info("Fetching breed with id={}", breedId);

        DogApiBreedDto dto = webClient.get()
                .uri(apiPath + "/breeds")
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToFlux(DogApiBreedDto.class)
                .filter(b -> String.valueOf(b.getId()).equals(breedId))
                .next()
                .block();

        if (Objects.isNull(dto)) {
            throw new BreedNotFoundException(breedId);
        }

        return Breed.builder()
                .id(String.valueOf(dto.getId()))
                .name(dto.getName())
                .averageWeight(Objects.nonNull(dto.getWeight()) ? dto.getWeight().getMetric() : null)
                .averageHeight(Objects.nonNull(dto.getHeight()) ? dto.getHeight().getMetric() : null)
                .lifeSpan(dto.getLifeSpan())
                .temperament(dto.getTemperament())
                .breedGroup(dto.getBreedGroup())
                .bredFor(dto.getBredFor())
                .build();
    }
}