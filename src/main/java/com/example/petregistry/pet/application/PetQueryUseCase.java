package com.example.petregistry.pet.application;

import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetFilterParams;

import java.util.Collection;

public interface PetQueryUseCase {
    Collection<Pet> list();

    Collection<Pet> list(PetFilterParams filterParams);
}