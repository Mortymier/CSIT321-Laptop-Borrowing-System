package com.appdev.lbs_springboot.controller;

import com.appdev.lbs_springboot.entity.StaffEntity;
import com.appdev.lbs_springboot.service.StaffService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staffs")
public class StaffController 
{
    @Autowired
    private StaffService staffService;

    // POST or CREATE
    @PostMapping
    public StaffEntity addStaff(@RequestBody StaffEntity staff)
    {
        return staffService.addStaff(staff);
    }

    // GET or READ
    @GetMapping
    public List<StaffEntity> getAllStaffs()
    {
        return staffService.getAllStaffs();
    }

    @GetMapping("/{id}")
    public Optional<StaffEntity> getStaffById(@PathVariable int id)
    {
        return staffService.getStaffById(id);
    }

    // PUT or UPDATE
    @PutMapping("/{id}")
    public StaffEntity updateStaff(@PathVariable int id, @RequestBody StaffEntity staff)
    {
        return staffService.updateStaff(id, staff);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable int id)
    {
        return staffService.deleteStaff(id);
    }
}
