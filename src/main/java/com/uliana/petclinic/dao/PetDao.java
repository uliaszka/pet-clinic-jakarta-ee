package com.uliana.petclinic.dao;
import com.uliana.petclinic.model.Pet;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface PetDao {
    void create(Pet pet);
    List<Pet> findAll();
    Pet findById(Long id);
    void update(Pet pet);
    void delete(Long id);
    List<Pet> filtered();

    List<Pet> findNewestEntries();
}

