package com.appdev.lbs_springboot.controller;

import com.appdev.lbs_springboot.entity.LaptopEntity;
import com.appdev.lbs_springboot.service.LaptopService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/laptops")
public class LaptopController 
{
    @Autowired
    private LaptopService laptopService;

    // POST or CREATE
    @PostMapping
    public LaptopEntity addLaptop(@RequestBody LaptopEntity laptop) 
    {
        return laptopService.addLaptop(laptop);
    }

    // GET or READ
    @GetMapping
    public List<LaptopEntity> getAllLaptops() 
    {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaptopEntity> getLaptopById(@PathVariable int id) 
    {
        return laptopService.getLaptopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT or UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<LaptopEntity> updateLaptop(@PathVariable int id, @RequestBody LaptopEntity laptop) 
    {
        try 
        {
            return ResponseEntity.ok(laptopService.updateLaptop(id, laptop));
        } 
        catch(RuntimeException ex) 
        {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable int id) 
    {
        laptopService.deleteLaptop(id);
        return ResponseEntity.noContent().build();
    }
}