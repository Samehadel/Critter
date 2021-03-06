package com.project.critter.pet;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = PetControllerHelper.convertPetDto(petDTO);

        petService.save(pet);

        return PetControllerHelper.convertPetEntity(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetControllerHelper.convertPetEntity(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();

        return PetControllerHelper.convertListOfPetEntities(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwner(ownerId);

        return PetControllerHelper.convertListOfPetEntities(pets);
    }
}
