package com.example.petregistry.pet.application;

import com.example.petregistry.breed.application.BreedApi;
import com.example.petregistry.breed.domain.Breed;
import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetPostDTO;
import com.example.petregistry.pet.infrastructure.PetRepository;
import com.example.petregistry.shared.Range;
import org.springframework.stereotype.Service;

@Service
public class RegisterPetService implements RegisterPetUseCase {

    private final PetRepository repository;
    private final BreedApi breedApi;

    public RegisterPetService(PetRepository repository, BreedApi breedApi) {
        this.repository = repository;
        this.breedApi = breedApi;
    }

    @Override
    public Pet register(PetPostDTO dto) {
        Breed breed = breedApi.fetchById(dto.getBreedId());

        Range weight = Range.parse(breed.getAverageWeight());
        Range height = Range.parse(breed.getAverageHeight());

        Pet pet = Pet.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .address(dto.getAddress())
                .city(dto.getCity())
                .breedId(breed.getId())
                .breedName(breed.getName())
                .lifeSpan(breed.getLifeSpan())
                .averageWeight(breed.getAverageWeight())
                .averageHeight(breed.getAverageHeight())
                .minWeight(weight.min())
                .maxWeight(weight.max())
                .minHeight(height.min())
                .maxHeight(height.max())
                .breedGroup(breed.getBreedGroup())
                .temperament(breed.getTemperament())
                .bredFor(breed.getBredFor())
                .build();

        return repository.save(pet);
    }

}