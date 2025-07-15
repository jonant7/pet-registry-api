package com.example.petregistry.breed.application;

import com.example.petregistry.breed.domain.Breed;
import org.springframework.stereotype.Service;

@Service
public class BreedQueryService {

    private final BreedApi breedApi;

    public BreedQueryService(BreedApi breedApi) {
        this.breedApi = breedApi;
    }

    public Breed getBreedById(String breedId) {
        return breedApi.fetchById(breedId);
    }
}