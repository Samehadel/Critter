package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    public List<Pet> findByOwner(Customer owner);
}
