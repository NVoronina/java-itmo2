package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.OwnerEntity;
import com.example.javaitmo2.repository.impl.OwnerInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long>, OwnerInterface {
}
