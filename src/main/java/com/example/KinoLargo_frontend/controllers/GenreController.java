package com.example.KinoLargo_frontend.controllers;

import com.example.KinoLargo_frontend.clients.GenreClient;
import com.example.KinoLargo_frontend.dtos.GenreDTO;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/genre")
public class GenreController {

    private final GenreClient genreClient;

    @GetMapping("/create")
    public String showCreateGenreForm(Model model) {
        model.addAttribute("region", new GenreDTO());
        return "Genre/form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute GenreDTO genreDTO, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        if (token == null) {
            return "redirect:/login";
        }
        genreClient.save(genreDTO, token);
        return "redirect:/genre/all";
    }

    @GetMapping("/all")
    public String listAll(Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<GenreDTO> genres = genreClient.findAll(token);
        model.addAttribute("genres", genres);
        return "Genre/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        GenreDTO genreDTO = genreClient.findById(id, token);
        model.addAttribute("genre", genreDTO);
        return "Genre/edit";
    }

    @PostMapping("/edit/{id}")
    public String submitEditRegionForm(@PathVariable Long id, @ModelAttribute GenreDTO genreDTO, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        genreClient.update(id, genreDTO, token);
        return "redirect:/genre/all";
    }
    @PostMapping("/delete/{id}")
    public String deleteRegion(@PathVariable Long id, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        genreClient.delete(id, token);
        return "redirect:/genre/all";
    }

}
