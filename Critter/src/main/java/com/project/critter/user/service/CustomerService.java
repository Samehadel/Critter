package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.exceptions.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetService petService;


    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findCustomerById(long ownerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(ownerId);

        if(!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("Customer Not Found For ID: " + ownerId);

        return optionalCustomer.get();
    }

    public Customer findOwnerByPetId(long petId){
        Pet pet = petService.getPetById(petId);

        return customerRepository.findByPets(pet);
    }
}
