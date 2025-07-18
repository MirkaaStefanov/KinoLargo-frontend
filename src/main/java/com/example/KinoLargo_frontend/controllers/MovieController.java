package com.example.KinoLargo_frontend.controllers;

import com.example.KinoLargo_frontend.clients.GenreClient;
import com.example.KinoLargo_frontend.clients.MovieClient;
import com.example.KinoLargo_frontend.dtos.GenreDTO;
import com.example.KinoLargo_frontend.dtos.MovieDTO;
import com.example.KinoLargo_frontend.enums.MovieType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/movie")
public class MovieController {

    private final MovieClient movieClient;
    private final GenreClient genreClient;

    @GetMapping("/all")
    public String getMenuItems(Model model, HttpServletRequest request) {

        String token = (String) request.getSession().getAttribute("sessionToken");

        try {
            // Fetch ALL menu items, filtering will happen on frontend
            List<MovieDTO> movies = movieClient.findAll(token);
            model.addAttribute("allMovies", movies);
        } catch (Exception e) {
            log.error("Error fetching all: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error loading movies: " + e.getMessage());
            model.addAttribute("allMovies", List.of());
        }

        model.addAttribute("movieTypeEnumValues", MovieType.values());


        return "Movie/all";
    }

    @GetMapping("/create")
    public String movieForm(Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");

        List<GenreDTO> genres = genreClient.findAll(token);

        model.addAttribute("createDTO", new MovieDTO());
        model.addAttribute("allGenres", genres);
        model.addAttribute("movieTypeEnumValues", MovieType.values());
        return "Movie/form";
    }

    @PostMapping("/create")
    public String createMenuItem(@ModelAttribute MovieDTO movieDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String token = (String) request.getSession().getAttribute("sessionToken");

        try {
            if (movieDTO.getImageFile() != null && !movieDTO.getImageFile().isEmpty()) {
                byte[] fileBytes = movieDTO.getImageFile().getBytes();
                String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
                movieDTO.setImage(encodedImage);
            } else if (movieDTO.getImage() != null && !movieDTO.getImage().isEmpty()) {

            } else {
                movieDTO.setImage(null);
                movieDTO.setImageFile(null);
            }

            movieClient.save(movieDTO, token);
            redirectAttributes.addFlashAttribute("successMessage", "Movie created successfully!");
        } catch (Exception e) {
            log.error("Error creating movie: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create movie: " + e.getMessage());
        }
        return "redirect:/movie/all";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable Long id, HttpServletRequest request, Model model) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        MovieDTO movieDTO = movieClient.findById(id, token);
        model.addAttribute("updateDTO", movieDTO);
        return "Movie/updateForm";
    }

    @PostMapping("/edit/{id}")
    public String updateSubmit(@PathVariable Long id, @ModelAttribute MovieDTO movieDTO, HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");

        MovieDTO existingMovieDTO = movieClient.findById(id, token);

        if (movieDTO.getImageFile() != null && !movieDTO.getImageFile().isEmpty()) {
            byte[] fileBytes = movieDTO.getImageFile().getBytes();
            String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
            movieDTO.setImage(encodedImage);
        } else {

            movieDTO.setImage(existingMovieDTO.getImage());
        }

        movieClient.update(id, movieDTO, token);
        return "redirect:/movie/all";
    }


    @GetMapping("/{id}")
    public MovieDTO findById(@PathVariable Long id, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        return movieClient.findById(id, token);
    }

    // Handles deleting a menu item
    @PostMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        try {
            movieClient.delete(id, token);
            redirectAttributes.addFlashAttribute("successMessage", "Movie deleted successfully!");
        } catch (Exception e) {
            log.error("Error deleting movie with ID {}: {}", id, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete movie: " + e.getMessage());
        }
        return "redirect:/movie/all";
    }


}
