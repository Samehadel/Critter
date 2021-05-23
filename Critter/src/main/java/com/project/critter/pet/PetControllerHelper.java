package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PetControllerHelper {
    /*
    Employee Conversion Methods
        convertPetDto: Converts PetDto to Pet entity
        convertPetEntity: Converts Pet entity to PetDto
        convertListOfPetEntities: Converts a list of Pet to a list of PetDto
     */
    public static Pet convertPetDto(PetDTO petDto){
        Pet pet = new Pet();

        BeanUtils.copyProperties(petDto, pet);

        pet.getOwner().setId(petDto.getOwnerId());

        if(pet.getId() < 1)
            pet.setId(null);

        return pet;
    }
    public static PetDTO convertPetEntity(Pet pet){
        PetDTO petDto = new PetDTO();

        BeanUtils.copyProperties(pet, petDto);

        petDto.setOwnerId(pet.getOwner().getId());

        return petDto;
    }
    public static List<PetDTO> convertListOfPetEntities(List<Pet> pets){
        List<PetDTO> petDtos = new ArrayList<>();
        for(Pet pet: pets){
            petDtos.add(convertPetEntity(pet));
        }

        return petDtos;
    }
}
