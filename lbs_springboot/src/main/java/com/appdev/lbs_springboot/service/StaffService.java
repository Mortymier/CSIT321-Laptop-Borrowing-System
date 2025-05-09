package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.StaffEntity;
import com.appdev.lbs_springboot.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public StaffService() {
        super();
    }

    // CREATE
    public StaffEntity addStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    // READ ALL
    public List<StaffEntity> getAllStaffs() {
        return staffRepository.findAll();
    }

    // READ BY ID
    public Optional<StaffEntity> getStaffById(int id) {
        return staffRepository.findById(id);
    }

    // READ BY EMAIL
    public StaffEntity getStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    // LOGIN
    public Optional<StaffEntity> loginStaff(String email, String password) {
        return staffRepository.findByEmailAndPassword(email, password);
    }

    // REGISTER
    public StaffEntity registerStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    // UPDATE
    public StaffEntity updateStaff(int id, StaffEntity updatedStaff) {
        StaffEntity staff = staffRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Staff not found"));

        staff.setFirstName(updatedStaff.getFirstName());
        staff.setLastName(updatedStaff.getLastName());
        staff.setEmail(updatedStaff.getEmail());
        staff.setPassword(updatedStaff.getPassword());

        return staffRepository.save(staff);
    }

    // DELETE
    public void deleteStaff(int id) {
        StaffEntity staff = staffRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Staff not found"));
        staffRepository.delete(staff);
    }
}
