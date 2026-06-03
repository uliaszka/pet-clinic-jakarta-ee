package com.uliana.petclinic.rest;

import com.uliana.petclinic.dao.PetDao;
import com.uliana.petclinic.model.Pet;
import com.uliana.petclinic.security.Secured;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.time.format.DateTimeParseException;
import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    @EJB
    private PetDao petDao;

    @GET
    public List<PetDto> getAll() {
        return petDao.findAll().stream()
                .map(PetDto::fromEntity)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Pet pet = petDao.findById(id);
        if (pet == null) {
            return notFound(id);
        }
        return Response.ok(PetDto.fromEntity(pet)).build();
    }

    @POST
    @Secured
    public Response create(PetDto dto) {
        try {
            Pet pet = dto.toEntity();
            pet.setId(null);
            petDao.create(pet);
            return Response.created(URI.create("/api/pets/" + pet.getId()))
                    .entity(PetDto.fromEntity(pet))
                    .build();
        } catch (DateTimeParseException e) {
            return badRequest("Dates must use ISO format: birthDate=YYYY-MM-DD.");
        }
    }

    @PUT
    @Path("/{id}")
    @Secured
    public Response update(@PathParam("id") Long id, PetDto dto) {
        Pet existing = petDao.findById(id);
        if (existing == null) {
            return notFound(id);
        }

        try {
            Pet pet = dto.toEntity();
            pet.setId(id);
            pet.setCreatedAt(existing.getCreatedAt());
            petDao.update(pet);
            return Response.ok(PetDto.fromEntity(pet)).build();
        } catch (DateTimeParseException e) {
            return badRequest("Dates must use ISO format: birthDate=YYYY-MM-DD.");
        }
    }

    private Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDto(message))
                .build();
    }

    private Response notFound(Long id) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorDto("Pet with id " + id + " was not found."))
                .build();
    }

    public static class ErrorDto {
        private String message;

        public ErrorDto() {
        }

        public ErrorDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
