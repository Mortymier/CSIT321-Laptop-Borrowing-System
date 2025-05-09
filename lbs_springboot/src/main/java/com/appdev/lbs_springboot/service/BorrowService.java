package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.BorrowEntity;
import com.appdev.lbs_springboot.repository.BorrowRepository;
import com.appdev.lbs_springboot.repository.StudentRepository;
import com.appdev.lbs_springboot.repository.StaffRepository;
import com.appdev.lbs_springboot.repository.LaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    // GET or READ
    public List<BorrowEntity> getBorrowRecords()
    {
        return brepo.findAll();
    }

    public Optional<BorrowEntity> getBorrowRecordById(int id)
    {
        return brepo.findById(id);
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

