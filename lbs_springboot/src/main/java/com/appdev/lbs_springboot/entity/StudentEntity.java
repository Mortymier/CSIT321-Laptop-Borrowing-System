package com.appdev.lbs_springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_students")
public class StudentEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid")
    private int id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String number;
    private String course;
    private int year;

    public StudentEntity()
    {
        super();
    }

    public StudentEntity(int id, String firstName, String lastName, String email, String password, String number, String course, int year)
    {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.number = number;
        this.course = course;
        this.year = year;
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

    public String getNumber()
    {
        return number;
    }

    public String getCourse()
    {
        return course;
    }

    public int getYear()
    {
        return year;
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

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}
