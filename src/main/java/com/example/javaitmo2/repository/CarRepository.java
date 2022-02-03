package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.CarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    CarEntity getByVinNumber(String vinNumber);

    Collection<CarEntity> findAllByBrandOrderByIdDesc(String brand);

    List<CarEntity> findAllBySeatsCountGreaterThan(Integer seat, Pageable pageable);
}
