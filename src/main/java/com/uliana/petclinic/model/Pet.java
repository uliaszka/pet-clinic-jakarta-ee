package com.uliana.petclinic.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String species;
    private LocalDate birthDate;
    private String ownerName;
    private String notes;


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
