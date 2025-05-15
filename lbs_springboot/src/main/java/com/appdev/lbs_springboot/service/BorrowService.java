package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.BorrowEntity;
import com.appdev.lbs_springboot.entity.LaptopEntity;
import com.appdev.lbs_springboot.entity.BorrowEntity.BorrowStatus;
import com.appdev.lbs_springboot.entity.StudentEntity;
import com.appdev.lbs_springboot.repository.BorrowRepository;
import com.appdev.lbs_springboot.repository.StudentRepository;
import com.appdev.lbs_springboot.repository.StaffRepository;
import com.appdev.lbs_springboot.repository.LaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import javax.naming.NameNotFoundException;

@Service
public class BorrowService 
{
    @Autowired
    BorrowRepository brepo;

    @Autowired
    StudentRepository sturepo;

    @Autowired
    StaffRepository starepo;

    @Autowired
    LaptopRepository laprepo;

    public BorrowService()
    {
        super();
    }

    // POST or CREATE
    public BorrowEntity postBorrowRecord(int studentid, int staffid, int laptopid, BorrowEntity borrow)
    {
        borrow.setStudent(sturepo.findById(studentid).get());
        borrow.setStaff(starepo.findById(staffid).get());
        borrow.setLaptop(laprepo.findById(laptopid).get());
        return brepo.save(borrow);
    }

    public BorrowEntity createBorrowRecord(String email, String model, BorrowEntity borrow)
    {
        BorrowEntity newBorrow = borrow;
        newBorrow.setStudent(sturepo.findByEmail(email));
        newBorrow.setLaptop(laprepo.findByModel(model));
        newBorrow.setStaff(starepo.findById(1).get());
        return brepo.save(newBorrow);
    }

    // GET or READ
    public List<BorrowEntity> getBorrowRecords()
    {
        return brepo.findAll();
    }

    public Optional<BorrowEntity> getBorrowRecordById(int id)
    {
        return brepo.findById(id);
    }

    // Get all borrow records of a specific student with approved status
    public List<BorrowEntity> getApprovedBorrowRecordsByStudentEmail(String email)
    {
        StudentEntity student = sturepo.findByEmail(email);
        BorrowStatus borrowStatus = BorrowStatus.APPROVED;
        return brepo.findByStudentAndBorrowStatus(student, borrowStatus);
    }

    // Get all borrow records of a specific student with pending status
    public List<BorrowEntity> getPendingBorrowRecordsByStudentEmail(String email)
    {
        StudentEntity student = sturepo.findByEmail(email);
        BorrowStatus borrowStatus = BorrowStatus.REVIEW;
        return brepo.findByStudentAndBorrowStatus(student, borrowStatus);
        //return brepo.findByStudent(student);
    }

    // Get all borrow records with a pending status
    public List<BorrowEntity> getPendingBorrowRecords()
    {
        BorrowStatus borrowStatus = BorrowStatus.REVIEW;
        return brepo.findByBorrowStatus(borrowStatus);
    }

    // PUT or UPDATE
    @SuppressWarnings("finally")
    public BorrowEntity putBorrowRecord(int id, BorrowEntity updatedBorrow)
    {
        BorrowEntity currentBorrow = new BorrowEntity();

        try
        {
            currentBorrow = brepo.findById(id).get();
            currentBorrow.setBorrowStatus(updatedBorrow.getBorrowStatus());
        }
        catch(NoSuchElementException e)
        {
            throw new NameNotFoundException("Borrow " + id + " does not exist!");
        }
        finally
        {
            return brepo.save(currentBorrow);
        }
    }

    // Update borrow status
    @SuppressWarnings("finally")
    public BorrowEntity updateBorrowStatus(BorrowStatus newStatus, BorrowEntity updatedBorrow)
    {
        BorrowEntity currentBorrow = new BorrowEntity();
        LocalDate newReturnDate = LocalDate.now().plusDays(7);
        Date newReturnDateSQL = Date.valueOf(newReturnDate);
        

        try
        {
            StudentEntity student = sturepo.findByEmail(updatedBorrow.getStudent().getEmail());
            LaptopEntity laptop = laprepo.findByModel(updatedBorrow.getLaptop().getModel());
            currentBorrow = brepo.findByStudentAndLaptop(student, laptop);
            currentBorrow.setBorrowStatus(newStatus);
            currentBorrow.setReturnDate(newReturnDate);
            
        }
        catch(NoSuchElementException e)
        {
            throw new NameNotFoundException("Borrow record does not exist!");
        }
        finally
        {
            return brepo.save(currentBorrow);
        }
    }

    // DELETE
    @SuppressWarnings("unused")
    public String deleteBorrowRecord(int id)
    {
        String msg = "";

        if(brepo.findById(id) != null)
        {
            brepo.deleteById(id);
            msg = "Borrow record " + id + " has been successfully deleted!";
        }
        else
        {
            msg = "Borrow record " + id + " does not exist!";
        }

        return msg;
    }
}

