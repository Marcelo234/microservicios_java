package com.unibe.marcelo.client;

import com.unibe.marcelo.dto.ApellidoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ontaneda", path = "/apellidos")
public interface ApellidoClient {

    @GetMapping("/{id}")
    ApellidoDto getById(@PathVariable("id") Long id);
}
