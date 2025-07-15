package com.example.petregistry.pet.application;

import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetFilterParams;
import com.example.petregistry.pet.infrastructure.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PetQueryServiceTest {

    private PetRepository repository;
    private PetQueryService service;

    @BeforeEach
    void setUp() {
        repository = mock(PetRepository.class);
        service = new PetQueryService(repository);
    }

    @Test
    void should_list_all_pets_when_no_filter() {
        when(repository.findAll()).thenReturn(List.of(
                Pet.builder().name("Max").build(),
                Pet.builder().name("Luna").build()
        ));

        var result = service.list();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void should_filter_pets_by_breed_id() {
        PetFilterParams params = new PetFilterParams();
        params.setBreedId("1");

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Rocky").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_filter_pets_by_breed_group() {
        PetFilterParams params = new PetFilterParams();
        params.setBreedGroup("Working");

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Rocky").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_filter_by_complete_age_range() {
        PetFilterParams params = new PetFilterParams();
        params.setMinAge(1);
        params.setMaxAge(5);

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Max").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_filter_by_weight_and_height_range() {
        PetFilterParams params = new PetFilterParams();
        params.setMinWeight(10.0);
        params.setMaxWeight(20.0);
        params.setMinHeight(30.0);
        params.setMaxHeight(50.0);

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(
                        Pet.builder().name("Max").build(),
                        Pet.builder().name("Luna").build()
                ));

        var result = service.list(params);

        assertEquals(2, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_not_apply_age_filter_with_only_minAge() {
        PetFilterParams params = new PetFilterParams();
        params.setMinAge(2);

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Max").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_not_apply_height_filter_with_only_minHeight() {
        PetFilterParams params = new PetFilterParams();
        params.setMinHeight(40.0);

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Max").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    void should_not_apply_weight_filter_with_only_minWeight() {
        PetFilterParams params = new PetFilterParams();
        params.setMinWeight(12.0);

        when(repository.findAll(any(Specification.class)))
                .thenReturn(List.of(Pet.builder().name("Rocky").build()));

        var result = service.list(params);

        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }
}