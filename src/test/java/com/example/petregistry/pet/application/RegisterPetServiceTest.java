package com.example.petregistry.pet.application;

import com.example.petregistry.breed.application.BreedApi;
import com.example.petregistry.breed.domain.Breed;
import com.example.petregistry.breed.domain.BreedNotFoundException;
import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetPostDTO;
import com.example.petregistry.pet.infrastructure.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterPetServiceTest {

    private BreedApi breedApi;
    private PetRepository repository;
    private RegisterPetService service;

    @BeforeEach
    void setUp() {
        breedApi = mock(BreedApi.class);
        repository = mock(PetRepository.class);
        service = new RegisterPetService(repository, breedApi);
    }

    @Test
    void should_register_pet_with_full_breed_data() {
        PetPostDTO dto = new PetPostDTO("Scooby Doo", (short) 2, "123 Test Street", "Test city",  "124");

        Breed mockBreed = Breed.builder()
                .id("124")
                .name("Great Dane")
                .averageWeight("50 - 86")
                .averageHeight("71 - 81")
                .lifeSpan("7 - 10 years")
                .temperament("Friendly, Devoted, Reserved, Gentle, Confident, Loving")
                .breedGroup("Working")
                .bredFor("Hunting & holding boars, Guardian")
                .build();

        when(breedApi.fetchById("124")).thenReturn(mockBreed);
        when(repository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pet result = service.register(dto);

        assertNotNull(result);
        assertEquals("Great Dane", result.getBreedName());
        assertEquals(50.0, result.getMinWeight());
        assertEquals(86.0, result.getMaxWeight());
        assertEquals(71.0, result.getMinHeight());
        assertEquals(81.0, result.getMaxHeight());
        assertEquals("Working", result.getBreedGroup());
        verify(repository).save(any(Pet.class));
    }

    @Test
    void should_throw_exception_when_breed_not_found() {
        PetPostDTO dto = new PetPostDTO("Max", (short) 3, "123 Test Street", "Test city", "999");
        when(breedApi.fetchById("999")).thenThrow(new BreedNotFoundException("999"));

        assertThrows(BreedNotFoundException.class, () -> service.register(dto));
        verify(repository, never()).save(any());
    }
}