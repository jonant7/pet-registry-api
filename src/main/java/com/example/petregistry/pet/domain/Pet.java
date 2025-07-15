package com.example.petregistry.pet.domain;

import com.example.petregistry.common.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "pets")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Pet extends AbstractEntity {

    private String name;
    private Short age;
    private String address;
    private String city;
    private String breedId;
    private String breedName;
    private String lifeSpan;
    private String averageWeight;
    private String averageHeight;
    private Double minWeight;
    private Double maxWeight;
    private Double minHeight;
    private Double maxHeight;
    private String breedGroup;
    private String temperament;
    private String bredFor;

}
