package com.project.critter.pet;

import com.project.critter.user.entities.Customer;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @Nationalized
    private String notes;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    private Customer owner = new Customer();

    @Enumerated(value = EnumType.STRING)
    private PetType petType;

    private LocalDate birthDate;

    public Pet() {
    }

    public Pet(long id) {
        this.id = id;
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

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public Customer getOwner() {
        return owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
