package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @Nationalized
    private String notes;

    private String phoneNumber;

    @OneToMany( fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<Pet> pets = new HashSet<>();

    public Customer() {
    }

    // Setters THEN Getters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    //Helper Method(s)
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }
}
