package com.example.petregistry.pet.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class PetFilterParams {
    private String breedGroup;
    private String breedId;
    private Integer minAge;
    private Integer maxAge;
    private Double minWeight;
    private Double maxWeight;
    private Double minHeight;
    private Double maxHeight;

    public boolean isEmpty() {
        return Objects.isNull(breedId) && Objects.isNull(breedGroup)
                && Objects.isNull(minAge) && Objects.isNull(maxAge)
                && Objects.isNull(minWeight) && Objects.isNull(maxWeight)
                && Objects.isNull(minHeight) && Objects.isNull(maxHeight);
    }
}