package com.unibe.marcelo.controller;

import com.unibe.marcelo.client.ApellidoClient;
import com.unibe.marcelo.dto.ApellidoDto;
import com.unibe.marcelo.dto.RespuestaCombinadaDto;
import com.unibe.marcelo.entity.Nombre;
import com.unibe.marcelo.service.NombreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nombres")
public class NombreController {

    private final NombreService nombreService;
    private final ApellidoClient apellidoClient;

    @Autowired
    public NombreController(NombreService nombreService, ApellidoClient apellidoClient) {
        this.nombreService = nombreService;
        this.apellidoClient = apellidoClient;
    }

    @GetMapping
    public ResponseEntity<List<Nombre>> getAll() {
        return ResponseEntity.ok(nombreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nombre> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(nombreService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Nombre> create(@RequestBody Nombre nombre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nombreService.save(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nombre> update(@PathVariable Long id, @RequestBody Nombre nombre) {
        try {
            return ResponseEntity.ok(nombreService.update(id, nombre));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            nombreService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/completo/{idNombre}/{idApellido}")
    public ResponseEntity<?> getCombinedDetails(
            @PathVariable Long idNombre,
            @PathVariable Long idApellido) {
        try {
            // Find local Nombre details
            Nombre nombre = nombreService.findById(idNombre);
            
            // Consume Apellido details from 'ontaneda' microservice using OpenFeign
            ApellidoDto apellido = null;
            try {
                apellido = apellidoClient.getById(idApellido);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY)
                        .body("Error al consumir el servicio de Apellidos (ontaneda) para el ID: " + idApellido + ". " + e.getMessage());
            }

            if (apellido == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el Apellido con ID: " + idApellido + " en el microservicio de Apellidos.");
            }

            // Map and combine
            RespuestaCombinadaDto combinada = RespuestaCombinadaDto.builder()
                    .idNombre(nombre.getId())
                    .nombre(nombre.getNombre())
                    .edad(nombre.getEdad())
                    .fechaNacimiento(nombre.getFechaNacimiento())
                    .idApellido(apellido.getId())
                    .apellido(apellido.getApellido())
                    .salario(apellido.getSalario())
                    .activo(apellido.getActivo())
                    .nombreCompleto(nombre.getNombre() + " " + apellido.getApellido())
                    .build();

            return ResponseEntity.ok(combinada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
