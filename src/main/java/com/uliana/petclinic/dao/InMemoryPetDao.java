package com.uliana.petclinic.dao;

import com.uliana.petclinic.model.Pet;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class InMemoryPetDao implements PetDao {

    private final List<Pet> pets = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        create(new Pet(null, "Milo", "Cat", LocalDate.of(2021,4,12), "Anna Novak", "Indoor cat. Likes calm visits."));
        create(new Pet(null, "Rex", "Dog", LocalDate.of(2020,9,3), "Marko Kranjc", "Annual vaccination planned."));
        create(new Pet(null, "Luna", "Rabbit", LocalDate.of(2022,1,20), "Eva Horvat", "Check teeth regularly."));
    }

    @Override
    public void create(Pet pet) {
        pet.setId(idGenerator.getAndIncrement());
        pets.add(pet);
    }

    @Override
    public List<Pet> findAll() {
        return List.copyOf(pets);
    }

    @Override
    public Pet findById(Long id) {
        if (id == null) {
            return null;
        }
        return pets.stream()
                .filter(pet -> id.equals(pet.getId()))
                .findFirst()
                .orElse(null);
    }
}
