package com.example.KinoLargo_frontend.clients;

import com.example.KinoLargo_frontend.dtos.GenreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "restaurant-authentication", url = "${backend.base-url}/genres")
public interface GenreClient {

    @PostMapping("/save")
    GenreDTO save(@RequestBody GenreDTO genreDTO, @RequestHeader(value = "Authorization", required = false) String auth);


    @GetMapping("/all")
    List<GenreDTO> findAll(@RequestHeader(value = "Authorization", required = false) String auth);

    @GetMapping("/findById/{id}")
    GenreDTO findById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth);

    @PutMapping("/edit/{id}")
    GenreDTO update(@PathVariable Long id, @RequestBody GenreDTO genreDTO, @RequestHeader(value = "Authorization", required = false) String auth);

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth);


}
