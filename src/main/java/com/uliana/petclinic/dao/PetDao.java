package com.uliana.petclinic.dao;

import com.uliana.petclinic.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetDao {

    private List<Pet> pets=new ArrayList<>();
    private Long nextId=1L;

    public void create(Pet pet){
        pet.setId(nextId);
        nextId++;
        pets.add(pet);
    }
    public List<Pet> findAll(){
        return pets;
    }
    public Pet findById(Long id){
        for(Pet pet : pets){
            if(pet.getId().equals(id)){
                return pet;
            }
        }
        return null;
    }

}
