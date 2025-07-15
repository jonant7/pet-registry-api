package com.example.petregistry.breed.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Breed {
    String id;
    String name;
    String averageWeight;
    String averageHeight;
    String lifeSpan;
    String temperament;
    String breedGroup;
    String bredFor;

    @JsonCreator
    public Breed(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("averageWeight") String averageWeight,
            @JsonProperty("averageHeight") String averageHeight,
            @JsonProperty("lifeSpan") String lifeSpan,
            @JsonProperty("temperament") String temperament,
            @JsonProperty("breedGroup") String breedGroup,
            @JsonProperty("bredFor") String bredFor) {
        this.id = id;
        this.name = name;
        this.averageWeight = averageWeight;
        this.averageHeight = averageHeight;
        this.lifeSpan = lifeSpan;
        this.temperament = temperament;
        this.breedGroup = breedGroup;
        this.bredFor = bredFor;
    }
}