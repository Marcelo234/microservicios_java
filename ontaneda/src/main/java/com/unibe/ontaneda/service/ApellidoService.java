package com.unibe.ontaneda.service;

import com.unibe.ontaneda.entity.Apellido;
import java.util.List;

public interface ApellidoService {
    List<Apellido> findAll();
    Apellido findById(Long id);
    Apellido save(Apellido apellido);
    Apellido update(Long id, Apellido apellido);
    void delete(Long id);
}
