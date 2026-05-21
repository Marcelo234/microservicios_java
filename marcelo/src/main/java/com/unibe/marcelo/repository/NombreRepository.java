package com.unibe.marcelo.repository;

import com.unibe.marcelo.entity.Nombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NombreRepository extends JpaRepository<Nombre, Long> {
}
