package com.uliana.petclinic.model;

import com.uliana.petclinic.converter.LocalDateAttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "birthdate")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate birthDate;

    @Column(name = "ownername")
    private String ownerName;

    private String notes;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedat")
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

    public Pet(Long id, String name, String species, LocalDate birthDate, String ownerName,
               String notes, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, name, species, birthDate, ownerName, notes);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void setUpdate(){
        if (createdAt == null) {
            createdAt = updatedAt != null ? updatedAt : LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAtFormatted() {
        return formatDateTime(createdAt);
    }

    public String getUpdatedAtFormatted() {
        return formatDateTime(updatedAt);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toString().replace("T", " ").substring(0, 16) : "";
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
