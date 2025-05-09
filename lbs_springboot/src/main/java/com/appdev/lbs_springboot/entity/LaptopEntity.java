package com.appdev.lbs_springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_laptops")
public class LaptopEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptopid")
    private int id;

    private String brand;
    private String model;
    private String memory;
    private String cpu;

    @Enumerated(EnumType.STRING)
    private LaptopStatus laptopStatus;      

    public enum LaptopStatus
    {
        AVAILABLE, BORROWED, REPAIR
    }

    public LaptopEntity()
    {
        super();
    }

    public LaptopEntity(int id, String brand, String model, String memory, String cpu, LaptopStatus laptopStatus)
    {
        super();
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.memory = memory;
        this.cpu = cpu;
        this.laptopStatus = laptopStatus;
    }

    // Getters
    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public String getMemory()
    {
        return memory;
    }

    public String getCpu()
    {
        return cpu;
    }

    public LaptopStatus getLaptopStatus()
    {
        return laptopStatus;
    }

    // Setters
    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setMemory(String memory)
    {
        this.memory = memory;
    }

    public void setCpu(String cpu)
    {
        this.cpu = cpu;
    }

    public void setLaptopStatus(LaptopStatus laptopStatus)
    {
        this.laptopStatus = laptopStatus;
    }
}
