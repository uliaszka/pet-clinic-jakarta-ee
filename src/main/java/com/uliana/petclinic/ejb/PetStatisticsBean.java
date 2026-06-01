package com.uliana.petclinic.ejb;

import com.uliana.petclinic.dao.PetDao;
import com.uliana.petclinic.model.Pet;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class PetStatisticsBean implements PetStatisticsRemote {

    @EJB
    private PetDao petDao;

    @Override
    public int countPets() {
        return petDao.findAll().size();
    }

    @Override
    public List<Pet> petsWithoutOwner() {
        return petDao.filtered();
    }

    @Override
    public List<Pet> newestEntries() {
        return petDao.findNewestEntries();
    }

}
