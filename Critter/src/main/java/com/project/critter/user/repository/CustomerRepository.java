package com.project.critter.user.repository;


import com.project.critter.pet.Pet;
import com.project.critter.user.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findByPets(Pet pet);
}
