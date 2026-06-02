package com.uliana.petclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;



import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p ORDER BY p.id")
@NamedQuery(name = "Pet.filtered", query = "SELECT p FROM Pet p WHERE p.ownerName IS NULL OR p.ownerName = '' ORDER BY p.id")

public class Pet implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private String ownerName;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public Pet(){};
    public Pet(Long id,String name,String species,LocalDate birthDate,String ownerName,String  notes){
        this.id=id;
        this.name=name;
        this.species=species;
        this.birthDate=birthDate;
        this.ownerName=ownerName;
        this.notes=notes;
    }

    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public String getSpecies(){
        return species;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public String getNotes(){
        return notes;
    }


    public void setName(String name){
        this.name=name;
    }

    public void setSpecies(String species){
        this.species=species;
    }

    public void setBirthDate(LocalDate birthDate){
        this.birthDate=birthDate;
    }

    public void setOwnerName(String ownerName){
        this.ownerName=ownerName;
    }

    public void setNotes(String notes){
        this.notes=notes;
    }


    @PrePersist
    public void prePersist(){
        createdAt =LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }
    @PreUpdate
    public void setUpdate(){
        updatedAt=LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public String getCreatedAtFormatted() {
        return createdAt != null ? createdAt.toString().replace("T", " ").substring(0, 16) : "";
    }

    public String getUpdatedAtFormatted() {
        return updatedAt != null ? updatedAt.toString().replace("T", " ").substring(0, 16) : "";
    }


    @Override
    public String toString(){
        return "Pet{" +
                "id=" + id +
                ", name='" + name+'\'' +
                ", species='" + species + '\'' +
                ", birthDate=" + birthDate +
                ", ownerName='" +ownerName + '\'' +
                ", notes='" + notes + '\'' +
                '}';
}

}
