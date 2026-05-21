package com.unibe.ontaneda.service.impl;

import com.unibe.ontaneda.entity.Apellido;
import com.unibe.ontaneda.repository.ApellidoRepository;
import com.unibe.ontaneda.service.ApellidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApellidoServiceImpl implements ApellidoService {

    private final ApellidoRepository apellidoRepository;

    @Autowired
    public ApellidoServiceImpl(ApellidoRepository apellidoRepository) {
        this.apellidoRepository = apellidoRepository;
    }

    @Override
    public List<Apellido> findAll() {
        return apellidoRepository.findAll();
    }

    @Override
    public Apellido findById(Long id) {
        return apellidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apellido no encontrado con ID: " + id));
    }

    @Override
    public Apellido save(Apellido apellido) {
        if (apellido.getActivo() == null) {
            apellido.setActivo(true);
        }
        return apellidoRepository.save(apellido);
    }

    @Override
    public Apellido update(Long id, Apellido apellidoDetails) {
        Apellido apellido = findById(id);
        apellido.setApellido(apellidoDetails.getApellido());
        apellido.setSalario(apellidoDetails.getSalario());
        if (apellidoDetails.getActivo() != null) {
            apellido.setActivo(apellidoDetails.getActivo());
        }
        return apellidoRepository.save(apellido);
    }

    @Override
    public void delete(Long id) {
        Apellido apellido = findById(id);
        apellidoRepository.delete(apellido);
    }
}
