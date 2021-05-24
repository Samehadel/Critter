package com.project.critter.pet;

import com.project.critter.user.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    public List<Pet> findByOwner(Customer owner);
}
