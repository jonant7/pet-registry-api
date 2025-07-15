package com.example.petregistry.breed.domain;

public class BreedNotFoundException extends RuntimeException {
    public BreedNotFoundException(String breedId) {
        super(String.format("Breed not found with id: %s", breedId));
    }
}