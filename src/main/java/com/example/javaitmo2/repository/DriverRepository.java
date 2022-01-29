package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.CarEntity;
import com.example.javaitmo2.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long>  {
}
