package com.appdev.lbs_springboot.controller;

import com.appdev.lbs_springboot.entity.StaffEntity;
import com.appdev.lbs_springboot.service.StaffService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/staffs")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<StaffEntity> registerStaff(@RequestBody StaffEntity staff) {
        StaffEntity newStaff = staffService.registerStaff(staff);
        return new ResponseEntity<>(newStaff, HttpStatus.CREATED);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<StaffEntity> loginStaff(@RequestParam String email, @RequestParam String password) {
        Optional<StaffEntity> staff = staffService.loginStaff(email, password);
        return staff.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Create
    @PostMapping
    public StaffEntity addStaff(@RequestBody StaffEntity staff) {
        return staffService.addStaff(staff);
    }

    // Read All
    @GetMapping
    public List<StaffEntity> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<StaffEntity> getStaffById(@PathVariable int id) {
        Optional<StaffEntity> staff = staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<StaffEntity> updateStaff(@PathVariable int id, @RequestBody StaffEntity staff) {
        StaffEntity updatedStaff = staffService.updateStaff(id, staff);
        return ResponseEntity.ok(updatedStaff);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
