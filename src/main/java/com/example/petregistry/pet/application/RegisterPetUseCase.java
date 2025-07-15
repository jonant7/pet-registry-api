package com.example.petregistry.pet.application;

import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetPostDTO;

public interface RegisterPetUseCase {
    Pet register(PetPostDTO dto);
}