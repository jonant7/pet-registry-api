package com.example.petregistry.breed.application;

import com.example.petregistry.breed.domain.Breed;
import com.example.petregistry.breed.domain.BreedNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BreedQueryServiceTest {

    private BreedApi breedApi;
    private BreedQueryService service;

    @BeforeEach
    void setUp() {
        breedApi = mock(BreedApi.class);
        service = new BreedQueryService(breedApi);
    }

    @Test
    void shouldReturnBreedWhenIdExists() {
        String breedId = "10";
        Breed expected = Breed.builder()
                .id(breedId)
                .name("Golden Retriever")
                .averageHeight("55 - 60")
                .averageWeight("25 - 34")
                .lifeSpan("10 - 12 years")
                .temperament("Friendly, Intelligent")
                .breedGroup("Sporting")
                .bredFor("Retrieving")
                .build();

        when(breedApi.fetchById(breedId)).thenReturn(expected);

        Breed result = service.getBreedById(breedId);

        assertNotNull(result);
        assertEquals(expected, result);
        verify(breedApi, times(1)).fetchById(breedId);
    }

    @Test
    void shouldThrowExceptionWhenBreedNotFound() {
        String breedId = "999";
        when(breedApi.fetchById(breedId)).thenThrow(new BreedNotFoundException(breedId));

        BreedNotFoundException exception = assertThrows(
                BreedNotFoundException.class,
                () -> service.getBreedById(breedId)
        );

        assertEquals("Breed not found with id: 999", exception.getMessage());
        verify(breedApi, times(1)).fetchById(breedId);
    }
}