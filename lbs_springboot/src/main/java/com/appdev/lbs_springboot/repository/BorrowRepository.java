package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.BorrowEntity;
import com.appdev.lbs_springboot.entity.LaptopEntity;
import com.appdev.lbs_springboot.entity.StudentEntity;
import com.appdev.lbs_springboot.entity.BorrowEntity.BorrowStatus;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Integer>
{
    // Find all borrow records for a specific laptop
    public List<BorrowEntity> findByLaptop(LaptopEntity laptop); 

    // Find all borrow records for a specific student
    public List<BorrowEntity> findByStudent(StudentEntity student);

    public BorrowEntity findByStudentAndLaptop(StudentEntity student, LaptopEntity laptop);

    // Find all borrow records for a specific student with a specific status
    public List<BorrowEntity> findByStudentAndBorrowStatus(StudentEntity student, BorrowStatus borrowStatus);

    // Find all borrow records with a specific borrow status
    public List<BorrowEntity> findByBorrowStatus(BorrowStatus borrowStatus);

    
}

