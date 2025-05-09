package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.StaffEntity;
import com.appdev.lbs_springboot.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import javax.naming.NameNotFoundException;

@Service
public class StaffService 
{
    @Autowired
    private StaffRepository staffRepository;

    public StaffService()
    {
        super();
    }

    // POST or CREATE
    public StaffEntity addStaff(StaffEntity staff)
    {
        return staffRepository.save(staff);
    }

    // GET or READ
    public List<StaffEntity> getAllStaffs()
    {
        return staffRepository.findAll();
    }

    public Optional<StaffEntity> getStaffById(int id)
    {
        return staffRepository.findById(id);
    }

    // PUT or UPDATE
    @SuppressWarnings("finally")
    public StaffEntity updateStaff(int id, StaffEntity updatedStaff)
    {
        StaffEntity currentStaff = new StaffEntity();

        try
        {
            currentStaff = staffRepository.findById(id).get();
            currentStaff.setEmail(updatedStaff.getEmail());
            currentStaff.setFirstName(updatedStaff.getFirstName());
            currentStaff.setLastName(updatedStaff.getLastName());
            currentStaff.setPassword(updatedStaff.getPassword());
        }
        catch(NoSuchElementException e)
        {
            throw new NameNotFoundException("Staff " + id + " does not exist!");
        }
        finally
        {
            return staffRepository.save(currentStaff);
        }
    }

    // DELETE
    @SuppressWarnings("unused")
    public String deleteStaff(int id)
    {
        String msg = "";

        if(staffRepository.findById(id) != null)
        {
            staffRepository.deleteById(id);
            msg = "Staff record " + id + " has been successfully deleted!";
        }
        else
        {
            msg = "Staff record " + id + " does not exist!";
        }

        return msg;
    }
}

