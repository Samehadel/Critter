package com.project.critter.pet;

import com.project.critter.user.entities.Customer;
import com.project.critter.user.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;



    public Pet save(Pet pet){
        Customer owner = customerService.findCustomerById(pet.getOwner().getId());

        owner.addPet(pet);

        return petRepository.save(pet);
    }

    public Pet getPetById (long petId){
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if(!optionalPet.isPresent())
            throw new PetNotFoundException("Pet Not Found For ID: " + petId);

        return optionalPet.get();
    }

    public List<Pet> getAllPets(){
        return (List<Pet>) petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long ownerId){
        // Use customer service to get a customer
        Customer owner = customerService.findCustomerById(ownerId);

        return petRepository.findByOwner(owner);
    }
}
