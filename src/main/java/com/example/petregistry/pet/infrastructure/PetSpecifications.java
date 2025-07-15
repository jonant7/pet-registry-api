package com.example.petregistry.pet.infrastructure;

import com.example.petregistry.pet.domain.Pet;
import org.springframework.data.jpa.domain.Specification;

public class PetSpecifications {

    public static Specification<Pet> hasBreedGroup(String group) {
        return (root, query, cb) -> cb.equal(root.get("breedGroup"), group);
    }

    public static Specification<Pet> hasBreedId(String breedId) {
        return (root, query, cb) -> cb.equal(root.get("breedId"), breedId);
    }

    public static Specification<Pet> ageBetween(Integer min, Integer max) {
        return (root, query, cb) -> cb.between(root.get("age"), min, max);
    }

    public static Specification<Pet> ageGreaterOrEqual(Integer min) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("age"), min);
    }

    public static Specification<Pet> ageLessOrEqual(Integer max) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("age"), max);
    }

    public static Specification<Pet> averageWeightBetween(Double min, Double max) {
        return (root, query, cb) -> cb.between(root.get("minWeight"), min, max);
    }

    public static Specification<Pet> averageWeightGreaterOrEqual(Double min) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("minWeight"), min);
    }

    public static Specification<Pet> averageWeightLessOrEqual(Double max) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("maxWeight"), max);
    }

    public static Specification<Pet> averageHeightBetween(Double min, Double max) {
        return (root, query, cb) -> cb.between(root.get("minHeight"), min, max);
    }

    public static Specification<Pet> averageHeightGreaterOrEqual(Double min) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("minHeight"), min);
    }

    public static Specification<Pet> averageHeightLessOrEqual(Double max) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("maxHeight"), max);
    }
}