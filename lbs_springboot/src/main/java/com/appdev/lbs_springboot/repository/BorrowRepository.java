package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.BorrowEntity;
import com.appdev.lbs_springboot.entity.LaptopEntity;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Integer>
{
    // Find all borrow records for a specific laptop
    public List<BorrowEntity> findByLaptop(LaptopEntity laptop); 
}

