package com.example.petregistry.breed.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogApiBreedDto {

    private int id;
    private String name;

    @JsonProperty("weight")
    private Weight weight;

    @JsonProperty("height")
    private Height height;

    @JsonProperty("life_span")
    private String lifeSpan;

    private String temperament;

    @JsonProperty("breed_group")
    private String breedGroup;

    @JsonProperty("bred_for")
    private String bredFor;

    @Data
    @NoArgsConstructor
    public static class Weight {
        private String metric;
    }

    @Data
    @NoArgsConstructor
    public static class Height {
        private String metric;
    }
}