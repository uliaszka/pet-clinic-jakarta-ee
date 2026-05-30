package com.uliana.petclinic.model;

import java.time.LocalDate;

public class Pet {
    private String name;
    private String species;
    private LocalDate birthDate;
    private String ownerName;
    private String notes;
    private Long id;

    public Pet(){};
    public Pet(String name,String species,LocalDate birthDate,String ownerName,String  notes){
        this.name=name;
        this.species=species;
        this.birthDate=birthDate;
        this.ownerName=ownerName;
        this.notes=notes;
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

    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id=id;
    }


}
