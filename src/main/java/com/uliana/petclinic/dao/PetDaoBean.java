package com.uliana.petclinic.dao;

import com.uliana.petclinic.model.Pet;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



import java.util.List;



@Stateless
public class PetDaoBean implements PetDao {

    @PersistenceContext(unitName = "petclinic_pu")
    private EntityManager em;



    @Override
    public void create(Pet pet) {
        em.persist(pet);
    }

    @Override
    public List<Pet> findAll() {
        return em.createNamedQuery("Pet.findAll",Pet.class).getResultList();
    }

    @Override
    public Pet findById(Long id) {
        if (id == null) {
            return null;
        }
        return em.find(Pet.class,id);
    }

    @Override
    public void update(Pet pet) {
       em.merge(pet);
    }

    @Override
    public void delete(Long id) {
        Pet pet =findById(id);
        if(pet!=null){
            em.remove(pet);
        }
    }

    @Override
    public List<Pet> filtered(){
        return em.createNamedQuery("Pet.filtered", Pet.class).getResultList();
    }

    @Override
     public List<Pet> findNewestEntries(){
        return em.createQuery(
                "SELECT p FROM Pet p ORDER BY p.createdAt DESC",
                Pet.class
        ).setMaxResults(5).getResultList();

    }
}

