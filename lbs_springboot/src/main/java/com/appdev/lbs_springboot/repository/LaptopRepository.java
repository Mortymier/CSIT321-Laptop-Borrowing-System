package com.appdev.lbs_springboot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appdev.lbs_springboot.entity.LaptopEntity;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Integer>
{

}