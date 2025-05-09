package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.LaptopEntity;
import com.appdev.lbs_springboot.repository.LaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopService 
{
    @Autowired
    private LaptopRepository laptopRepository;

    public LaptopService()
    {
        super();
    }

    // POST or CREATE
    public LaptopEntity addLaptop(LaptopEntity laptop) 
    {
        return laptopRepository.save(laptop);
    }

    // GET or READ
    public List<LaptopEntity> getAllLaptops() 
    {
        return laptopRepository.findAll();
    }

    public Optional<LaptopEntity> getLaptopById(int id) 
    {
        return laptopRepository.findById(id);
    }

    // PUT or UPDATE
    public LaptopEntity updateLaptop(int id, LaptopEntity updatedLaptop) 
    {
        return laptopRepository.findById(id).map(existing -> {
            existing.setBrand(updatedLaptop.getBrand());
            existing.setModel(updatedLaptop.getModel());
            existing.setMemory(updatedLaptop.getMemory());
            existing.setCpu(updatedLaptop.getCpu());
            existing.setLaptopStatus(updatedLaptop.getLaptopStatus());
            return laptopRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Laptop not found with ID: " + id));
    }

    // DELETE
    public void deleteLaptop(int id) 
    {
        laptopRepository.deleteById(id);
    }
}
