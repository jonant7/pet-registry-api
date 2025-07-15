package com.example.petregistry.pet.application;

import com.example.petregistry.pet.domain.Pet;
import com.example.petregistry.pet.domain.PetFilterParams;
import com.example.petregistry.pet.infrastructure.PetRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

import static com.example.petregistry.pet.infrastructure.PetSpecifications.*;

@Service
public class PetQueryService implements PetQueryUseCase {

    private final PetRepository repository;

    public PetQueryService(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Pet> list() {
        return repository.findAll();
    }

    @Override
    public Collection<Pet> list(PetFilterParams filterParams) {
        Specification<Pet> spec = (root, query, builder) -> null;

        if (Objects.nonNull(filterParams.getBreedId())) {
            spec = spec.and(hasBreedId(filterParams.getBreedId()));
        }
        if (Objects.nonNull(filterParams.getBreedGroup())) {
            spec = spec.and(hasBreedGroup(filterParams.getBreedGroup()));
        }
        if (Objects.nonNull(filterParams.getMinAge())) {
            spec = spec.and(ageGreaterOrEqual(filterParams.getMinAge()));
        }
        if (Objects.nonNull(filterParams.getMaxAge())) {
            spec = spec.and(ageLessOrEqual(filterParams.getMaxAge()));
        }
        if (Objects.nonNull(filterParams.getMinWeight())) {
            spec = spec.and(averageWeightGreaterOrEqual(filterParams.getMinWeight()));
        }
        if (Objects.nonNull(filterParams.getMaxWeight())) {
            spec = spec.and(averageWeightLessOrEqual(filterParams.getMaxWeight()));
        }
        if (Objects.nonNull(filterParams.getMinHeight())) {
            spec = spec.and(averageHeightGreaterOrEqual(filterParams.getMinHeight()));
        }
        if (Objects.nonNull(filterParams.getMaxHeight())) {
            spec = spec.and(averageHeightLessOrEqual(filterParams.getMaxHeight()));
        }
        return repository.findAll(spec);
    }
}