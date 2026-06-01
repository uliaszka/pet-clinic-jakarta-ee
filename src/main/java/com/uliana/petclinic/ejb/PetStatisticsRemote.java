package com.uliana.petclinic.ejb;

import com.uliana.petclinic.model.Pet;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PetStatisticsRemote {
    long countPets();
    List<Pet> petsWithoutOwner();
    List<Pet> newestEntries();
}

