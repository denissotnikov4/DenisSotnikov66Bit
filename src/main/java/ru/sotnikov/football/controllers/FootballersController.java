package ru.sotnikov.football.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sotnikov.football.dao.FootballerDAO;
import ru.sotnikov.football.models.Footballer;

import javax.validation.Valid;

@Controller
public class FootballersController {

    private final FootballerDAO footballerDAO;

    @Autowired
    public FootballersController(FootballerDAO footballerDAO) {
        this.footballerDAO = footballerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("footballers", footballerDAO.index());
        return "footballer/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("footballer", footballerDAO.show(id));
        return "footballer/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("footballer") Footballer footballer) {
        return "footballer/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("footballer") @Valid Footballer footballer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "footballer/new";

        footballerDAO.save(footballer);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("footballer", footballerDAO.show(id));
        return "footballer/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("footballer") @Valid Footballer footballer, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "footballer/edit";

        footballerDAO.update(id, footballer);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        footballerDAO.delete(id);
        return "redirect:/";
    }
}
