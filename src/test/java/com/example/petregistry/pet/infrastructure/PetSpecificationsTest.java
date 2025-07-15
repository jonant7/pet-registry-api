package com.example.petregistry.pet.infrastructure;

import com.example.petregistry.pet.domain.Pet;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PetSpecificationsTest {

    private Root<Pet> root;
    private CriteriaQuery<?> query;
    private CriteriaBuilder cb;
    private Predicate predicate;

    private Path<String> stringPath;
    private Path<Integer> intPath;
    private Path<Double> doublePath;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        root = mock(Root.class);
        query = mock(CriteriaQuery.class);
        cb = mock(CriteriaBuilder.class);
        predicate = mock(Predicate.class);

        stringPath = mock(Path.class);
        intPath    = mock(Path.class);
        doublePath = mock(Path.class);

        when(root.get("breedGroup")).thenReturn((Path) stringPath);
        when(root.get("breedId")).thenReturn((Path) stringPath);
        when(root.get("age")).thenReturn((Path) intPath);
        when(root.get("minWeight")).thenReturn((Path) doublePath);
        when(root.get("maxWeight")).thenReturn((Path) doublePath);
        when(root.get("minHeight")).thenReturn((Path) doublePath);
        when(root.get("maxHeight")).thenReturn((Path) doublePath);

        when(cb.equal(eq(stringPath), anyString())).thenReturn(predicate);

        when(cb.between(eq(intPath), anyInt(), anyInt())).thenReturn(predicate);
        when(cb.greaterThanOrEqualTo(eq(intPath), anyInt())).thenReturn(predicate);
        when(cb.lessThanOrEqualTo(eq(intPath), anyInt())).thenReturn(predicate);

        when(cb.between(eq(doublePath), anyDouble(), anyDouble())).thenReturn(predicate);
        when(cb.greaterThanOrEqualTo(eq(doublePath), anyDouble())).thenReturn(predicate);
        when(cb.lessThanOrEqualTo(eq(doublePath), anyDouble())).thenReturn(predicate);
    }

    @Test
    void hasBreedGroup_shouldUseEqualOnBreedGroup() {
        var spec = PetSpecifications.hasBreedGroup("Working");
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("breedGroup");
        verify(cb).equal(stringPath, "Working");
    }

    @Test
    void hasBreedId_shouldUseEqualOnBreedId() {
        var spec = PetSpecifications.hasBreedId("123");
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("breedId");
        verify(cb).equal(stringPath, "123");
    }

    @Test
    void ageBetween_shouldUseBetweenOnAge() {
        var spec = PetSpecifications.ageBetween(1, 5);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("age");
        verify(cb).between(intPath, 1, 5);
    }

    @Test
    void ageGreaterOrEqual_shouldUseGreaterThanOrEqualToOnAge() {
        var spec = PetSpecifications.ageGreaterOrEqual(2);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("age");
        verify(cb).greaterThanOrEqualTo(intPath, 2);
    }

    @Test
    void ageLessOrEqual_shouldUseLessThanOrEqualToOnAge() {
        var spec = PetSpecifications.ageLessOrEqual(10);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("age");
        verify(cb).lessThanOrEqualTo(intPath, 10);
    }

    @Test
    void averageWeightBetween_shouldUseBetweenOnMinWeight() {
        var spec = PetSpecifications.averageWeightBetween(5.5, 20.0);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("minWeight");
        verify(cb).between(doublePath, 5.5, 20.0);
    }

    @Test
    void averageWeightGreaterOrEqual_shouldUseGreaterThanOrEqualToOnMinWeight() {
        var spec = PetSpecifications.averageWeightGreaterOrEqual(7.2);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("minWeight");
        verify(cb).greaterThanOrEqualTo(doublePath, 7.2);
    }

    @Test
    void averageWeightLessOrEqual_shouldUseLessThanOrEqualToOnMaxWeight() {
        var spec = PetSpecifications.averageWeightLessOrEqual(30.0);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("maxWeight");
        verify(cb).lessThanOrEqualTo(doublePath, 30.0);
    }

    @Test
    void averageHeightBetween_shouldUseBetweenOnMinHeight() {
        var spec = PetSpecifications.averageHeightBetween(10.0, 40.0);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("minHeight");
        verify(cb).between(doublePath, 10.0, 40.0);
    }

    @Test
    void averageHeightGreaterOrEqual_shouldUseGreaterThanOrEqualToOnMinHeight() {
        var spec = PetSpecifications.averageHeightGreaterOrEqual(12.5);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("minHeight");
        verify(cb).greaterThanOrEqualTo(doublePath, 12.5);
    }

    @Test
    void averageHeightLessOrEqual_shouldUseLessThanOrEqualToOnMaxHeight() {
        var spec = PetSpecifications.averageHeightLessOrEqual(50.0);
        var result = spec.toPredicate(root, query, cb);

        assertThat(result).isSameAs(predicate);
        verify(root).get("maxHeight");
        verify(cb).lessThanOrEqualTo(doublePath, 50.0);
    }
}