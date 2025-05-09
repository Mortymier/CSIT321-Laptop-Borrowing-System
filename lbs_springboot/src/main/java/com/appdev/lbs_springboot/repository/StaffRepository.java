package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.StaffEntity;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer>
{
    public StaffEntity findByEmail(String email);
    public Optional<StaffEntity> findByEmailAndPassword(String email, String password);
}
