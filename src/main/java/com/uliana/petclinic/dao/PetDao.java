package com.uliana.petclinic.dao;
import com.uliana.petclinic.model.Pet;
import java.util.List;

public interface PetDao {
    void create(Pet pet);
    List<Pet> findAll();
    Pet findById(Long id);
}
