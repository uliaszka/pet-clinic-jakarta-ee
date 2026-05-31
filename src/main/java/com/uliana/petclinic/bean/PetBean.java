package com.uliana.petclinic.bean;

import com.uliana.petclinic.dao.PetDao;
import com.uliana.petclinic.model.Pet;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("petBean")
@ViewScoped
public class PetBean implements Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private PetDao petDao;

    private Pet pet = new Pet();
    private Long id;
    private Pet selectedPet;

    public List<Pet> getPets() {
        return petDao.findAll();
    }

    public String save() {
        if (pet.getId() == null) {
            petDao.create(pet);
        } else {
            petDao.update(pet);
        }
        pet = new Pet();
        return "listView?faces-redirect=true";
    }

    public void loadPet() {
        selectedPet = petDao.findById(id);
    }

    public void loadFormPet() {
        if (id != null) {
            Pet existing = petDao.findById(id);
            if (existing != null) {
                pet = new Pet(existing.getId(), existing.getName(), existing.getSpecies(),
                        existing.getBirthDate(), existing.getOwnerName(), existing.getNotes());
            }
        }
    }


    public String delete(Long id) {
        petDao.delete(id);
        return "listView?faces-redirect=true";
    }

    public List<Pet> getFiltered(){
         return petDao.filtered();
    }

    public Pet getPet() {return pet;}
    public void setPet(Pet pet) {this.pet = pet;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Pet getSelectedPet() {return selectedPet;}
    public void setSelectedPet(Pet selectedPet) {this.selectedPet = selectedPet;}
}
