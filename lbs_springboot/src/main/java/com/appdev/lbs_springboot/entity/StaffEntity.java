package com.appdev.lbs_springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_staffs")
public class StaffEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public StaffEntity()
    {
        super();
    }

    public StaffEntity(int id, String firstName, String lastName, String email, String password)
    {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    // Setters
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
