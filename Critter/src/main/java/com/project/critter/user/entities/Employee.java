package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @CollectionTable(name = "employee_skills",
            joinColumns = {@JoinColumn(name = "employee_id")})
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @CollectionTable(name = "employee_availability",
            joinColumns = {@JoinColumn(name = "employee_id")})
    @Enumerated(value = EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;


    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }
    // Setters THEN Getters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
}
