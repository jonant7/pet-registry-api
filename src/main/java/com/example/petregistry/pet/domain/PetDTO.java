package com.example.petregistry.pet.domain;

import com.example.petregistry.common.domain.AbstractEntityDTO;
import lombok.Getter;

@Getter
public class PetDTO extends AbstractEntityDTO {
    private final String name;
    private final Short age;
    private final String address;
    private final String city;
    private final String breed;
    private final String breedId;
    private final String lifeSpan;
    private final String weight;
    private final String height;
    private final String breedGroup;
    private final String temperament;
    private final String bredFor;

    public PetDTO (Pet pet) {
        super(pet.getId());
        this.name = pet.getName();
        this.age = pet.getAge();
        this.address = pet.getAddress();
        this.city = pet.getCity();
        this.breed = pet.getBreedName();
        this.breedId = pet.getBreedId();
        this.lifeSpan = pet.getLifeSpan();
        this.weight = pet.getAverageWeight();
        this.height = pet.getAverageHeight();
        this.breedGroup = pet.getBreedGroup();
        this.temperament = pet.getTemperament();
        this.bredFor = pet.getBredFor();
    }
}
