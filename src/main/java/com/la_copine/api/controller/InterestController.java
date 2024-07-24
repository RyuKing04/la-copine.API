package com.la_copine.api.controller;

import com.la_copine.api.model.Interest;
import com.la_copine.api.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    InterestService interestService;

    @RequestMapping()
    public List<Interest> getAll() {
        return interestService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Interest> getById(@PathVariable("id") Integer id){
        return interestService.getById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Interest interest){
        interestService.saveOrUpdate(interest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        interestService.delete(id);
    }
}
