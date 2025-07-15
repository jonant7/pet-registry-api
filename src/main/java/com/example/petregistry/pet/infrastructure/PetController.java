package com.example.petregistry.pet.infrastructure;

import com.example.petregistry.pet.application.PetQueryUseCase;
import com.example.petregistry.pet.application.RegisterPetUseCase;
import com.example.petregistry.pet.domain.PetDTO;
import com.example.petregistry.pet.domain.PetFilterParams;
import com.example.petregistry.pet.domain.PetPostDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final RegisterPetUseCase registerPetUseCase;
    private final PetQueryUseCase petQueryUseCase;

    public PetController(RegisterPetUseCase registerPetUseCase, PetQueryUseCase petQueryUseCase) {
        this.registerPetUseCase = registerPetUseCase;
        this.petQueryUseCase = petQueryUseCase;
    }

    @GetMapping
    public ResponseEntity<Collection<PetDTO>> list(@ModelAttribute PetFilterParams params) {
        final var pets = (params.isEmpty() ? petQueryUseCase.list() : petQueryUseCase.list(params))
                .stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<PetDTO> register(@Valid @RequestBody final PetPostDTO dto) {
        final var pet = registerPetUseCase.register(dto);
        return ResponseEntity.ok(new PetDTO(pet));
    }
}