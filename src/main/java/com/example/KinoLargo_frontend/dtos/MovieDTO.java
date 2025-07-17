package com.example.KinoLargo_frontend.dtos;

import com.example.KinoLargo_frontend.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long id;

    private String name;

    private int durationMins;

    private MovieType movieType;

    private GenreDTO genre;

    private double price;

    private String image;
    @JsonIgnore
    private transient MultipartFile imageFile;
}
