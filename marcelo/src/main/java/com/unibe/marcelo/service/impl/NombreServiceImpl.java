package com.unibe.marcelo.service.impl;

import com.unibe.marcelo.entity.Nombre;
import com.unibe.marcelo.repository.NombreRepository;
import com.unibe.marcelo.service.NombreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NombreServiceImpl implements NombreService {

    private final NombreRepository nombreRepository;

    @Autowired
    public NombreServiceImpl(NombreRepository nombreRepository) {
        this.nombreRepository = nombreRepository;
    }

    @Override
    public List<Nombre> findAll() {
        return nombreRepository.findAll();
    }

    @Override
    public Nombre findById(Long id) {
        return nombreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nombre no encontrado con ID: " + id));
    }

    @Override
    public Nombre save(Nombre nombre) {
        return nombreRepository.save(nombre);
    }

    @Override
    public Nombre update(Long id, Nombre nombreDetails) {
        Nombre nombre = findById(id);
        nombre.setNombre(nombreDetails.getNombre());
        nombre.setEdad(nombreDetails.getEdad());
        nombre.setFechaNacimiento(nombreDetails.getFechaNacimiento());
        return nombreRepository.save(nombre);
    }

    @Override
    public void delete(Long id) {
        Nombre nombre = findById(id);
        nombreRepository.delete(nombre);
    }
}
