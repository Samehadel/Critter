package com.project.critter.user.service;


import com.project.critter.pet.Pet;
import com.project.critter.pet.PetService;
import com.project.critter.user.entities.Customer;
import com.project.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetService petService;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findCustomerById(long ownerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(ownerId);

        if(!optionalCustomer.isPresent())
            throw new com.udacity.jdnd.course3.critter.user.exceptions.CustomerNotFoundException("Customer Not Found For ID: " + ownerId);

        return optionalCustomer.get();
    }

    public Customer findOwnerByPetId(long petId){
        Pet pet = petService.getPetById(petId);

        return customerRepository.findByPets(pet);
    }
}
