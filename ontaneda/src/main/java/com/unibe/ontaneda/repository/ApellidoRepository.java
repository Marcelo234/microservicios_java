package com.unibe.ontaneda.repository;

import com.unibe.ontaneda.entity.Apellido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApellidoRepository extends JpaRepository<Apellido, Long> {
}
