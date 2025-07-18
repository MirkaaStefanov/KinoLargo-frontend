package com.example.KinoLargo_frontend.clients;

import com.example.KinoLargo_frontend.dtos.GenreDTO;
import com.example.KinoLargo_frontend.dtos.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "kino-movies", url = "${backend.base-url}/movies")
public interface MovieClient {

    @PostMapping("/save")
    MovieDTO save(@RequestBody MovieDTO movieDTO, @RequestHeader(value = "Authorization", required = false) String auth);

    @GetMapping("/all")
    List<MovieDTO> findAll(@RequestHeader(value = "Authorization", required = false) String auth);

    @GetMapping("/findById/{id}")
    MovieDTO findById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth);

    @PutMapping("/edit/{id}")
    MovieDTO update(@PathVariable Long id, @RequestBody MovieDTO movieDTO, @RequestHeader(value = "Authorization", required = false) String auth);

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String auth);

}
