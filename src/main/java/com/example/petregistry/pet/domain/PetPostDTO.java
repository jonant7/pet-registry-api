package com.example.petregistry.pet.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PetPostDTO {

    @NotNull
    @NotEmpty
    private final String name;
    @NotNull
    private final Short age;
    @NotNull
    @NotEmpty
    private final String address;
    @NotNull
    @NotEmpty
    private final String city;
    @NotNull
    @NotEmpty
    private final String breedId;

}