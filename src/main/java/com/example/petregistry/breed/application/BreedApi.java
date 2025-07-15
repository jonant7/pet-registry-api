package com.example.petregistry.breed.application;

import com.example.petregistry.breed.domain.Breed;

public interface BreedApi {
    Breed fetchById(String breedId);
}