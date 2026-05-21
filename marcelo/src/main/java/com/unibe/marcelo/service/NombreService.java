package com.unibe.marcelo.service;

import com.unibe.marcelo.entity.Nombre;
import java.util.List;

public interface NombreService {
    List<Nombre> findAll();
    Nombre findById(Long id);
    Nombre save(Nombre nombre);
    Nombre update(Long id, Nombre nombre);
    void delete(Long id);
}
