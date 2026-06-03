package com.uliana.petclinic.dao;

import com.uliana.petclinic.model.Pet;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



import java.time.LocalDateTime;
import java.util.List;



@Stateless
public class PetDaoBean implements PetDao {

    @PersistenceContext(unitName = "petclinic_pu")
    private EntityManager em;



    @Override
    public void create(Pet pet) {
        LocalDateTime now = LocalDateTime.now();
        pet.setCreatedAt(now);
        pet.setUpdatedAt(now);
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
       if (pet.getCreatedAt() == null) {
           pet.setCreatedAt(LocalDateTime.now());
       }
       pet.setUpdatedAt(LocalDateTime.now());
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
                "SELECT p FROM Pet p ORDER BY p.createdAt DESC NULLS LAST",
                Pet.class
        ).setMaxResults(5).getResultList();

    }

    @Override
    public  long countPets(){
        return em.createQuery(
                "SELECT COUNT(p) FROM Pet p",
                Long.class
        ).getSingleResult();
    }
}

