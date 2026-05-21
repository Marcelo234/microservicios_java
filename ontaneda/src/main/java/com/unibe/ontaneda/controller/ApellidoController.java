package com.unibe.ontaneda.controller;

import com.unibe.ontaneda.entity.Apellido;
import com.unibe.ontaneda.service.ApellidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apellidos")
public class ApellidoController {

    private final ApellidoService apellidoService;

    @Autowired
    public ApellidoController(ApellidoService apellidoService) {
        this.apellidoService = apellidoService;
    }

    @GetMapping
    public ResponseEntity<List<Apellido>> getAll() {
        return ResponseEntity.ok(apellidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apellido> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(apellidoService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Apellido> create(@RequestBody Apellido apellido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apellidoService.save(apellido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apellido> update(@PathVariable Long id, @RequestBody Apellido apellido) {
        try {
            return ResponseEntity.ok(apellidoService.update(id, apellido));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            apellidoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
