package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>  {
    CarEntity getByVinNumber(String vinNumber);
}
