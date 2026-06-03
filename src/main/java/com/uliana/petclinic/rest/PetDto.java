package com.uliana.petclinic.rest;

import com.uliana.petclinic.model.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PetDto {

    private Long id;
    private String name;
    private String species;
    private String birthDate;
    private String ownerName;
    private String notes;
    private String createdAt;
    private String updatedAt;

    public static PetDto fromEntity(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setSpecies(pet.getSpecies());
        dto.setBirthDate(formatDate(pet.getBirthDate()));
        dto.setOwnerName(pet.getOwnerName());
        dto.setNotes(pet.getNotes());
        dto.setCreatedAt(formatDateTime(pet.getCreatedAt()));
        dto.setUpdatedAt(formatDateTime(pet.getUpdatedAt()));
        return dto;
    }

    public Pet toEntity() {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setSpecies(species);
        pet.setBirthDate(parseDate(birthDate));
        pet.setOwnerName(ownerName);
        pet.setNotes(notes);
        pet.setCreatedAt(parseDateTime(createdAt));
        pet.setUpdatedAt(parseDateTime(updatedAt));
        return pet;
    }

    private static String formatDate(LocalDate value) {
        return value == null ? null : value.toString();
    }

    private static String formatDateTime(LocalDateTime value) {
        return value == null ? null : value.toString();
    }

    private static LocalDate parseDate(String value) {
        return value == null || value.isBlank() ? null : LocalDate.parse(value);
    }

    private static LocalDateTime parseDateTime(String value) {
        return value == null || value.isBlank() ? null : LocalDateTime.parse(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
