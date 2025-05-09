package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer>
{

}
