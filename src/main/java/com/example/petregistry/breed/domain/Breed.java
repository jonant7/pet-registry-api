package com.example.petregistry.breed.domain;

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
}