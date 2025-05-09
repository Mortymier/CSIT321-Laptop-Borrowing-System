package com.appdev.lbs_springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_borrows")
public class BorrowEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowid")
    private int id;

    private String reason;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus borrowStatus;

    public enum BorrowStatus
    {
        REVIEW, APPROVED, REJECTED, RETURNED, LOST
    }

    @ManyToOne
    @JoinColumn(name = "fk_studentid")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "fk_staffid")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "fk_laptopid")
    private LaptopEntity laptop;

    public BorrowEntity()
    {
        super();
    }

    public BorrowEntity(int id, String reason, LocalDate borrowDate, LocalDate returnDate, BorrowStatus borrowStatus, StudentEntity student, StaffEntity staff, LaptopEntity laptop)
    {
        super();
        this.id = id;
        this.reason = reason;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrowStatus = borrowStatus;
        this.student = student;
        this.staff = staff;
        this.laptop = laptop;
    }

    // Getters
    public String getReason()
    {
        return reason;
    }

    public LocalDate getBorrowDate()
    {
        return borrowDate;
    }

    public LocalDate getReturnDate()
    {
        return returnDate;
    }

    public BorrowStatus getBorrowStatus()
    {
        return borrowStatus;
    }

    public StudentEntity getStudent()
    {
        return student;
    }

    public StaffEntity getStaff()
    {
        return staff;
    }

    public LaptopEntity getLaptop()
    {
        return laptop;
    }

    // Setters
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public void setBorrowDate(LocalDate borrowDate)
    {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate)
    {
        this.returnDate = returnDate;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus)
    {
        this.borrowStatus = borrowStatus;
    }

    public void setStudent(StudentEntity student)
    {
        this.student = student;
    }

    public void setStaff(StaffEntity staff)
    {
        this.staff = staff;
    }

    public void setLaptop(LaptopEntity laptop)
    {
        this.laptop = laptop;
    }
}