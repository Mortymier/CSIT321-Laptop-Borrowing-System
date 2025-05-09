package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.StudentEntity;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> 
{
    public StudentEntity findByEmail(String email);
    public Optional<StudentEntity> findByEmailAndPassword(String email, String password);
}
