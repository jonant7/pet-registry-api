package com.example.petregistry.breed.infrastructure;

import com.example.petregistry.breed.application.BreedQueryService;
import com.example.petregistry.breed.domain.Breed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breeds")
public class BreedController {
    private final BreedQueryService service;

    public BreedController(BreedQueryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Breed get(@PathVariable String id) {
        return service.getBreedById(id);
    }
}
