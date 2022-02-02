package com.example.javaitmo2.repository;

import com.example.javaitmo2.entity.OwnerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OwnerRepository {

    private EntityManager em;
    private ModelMapper modelMapper;

    public OwnerRepository(EntityManager em, ModelMapper modelMapper) {
        this.em = em;
        this.modelMapper = modelMapper;
    }

    public OwnerEntity getOwnerByCarVin(String vinNumber) {
        Object result = this.em
                .createNativeQuery(
                        "SELECT o.* FROM owners o " +
                        " INNER JOIN cars c ON o.id = c.owner_id" +
                        " WHERE c.vin_number = :vin")
                .setParameter("vin", vinNumber)
                .getFirstResult();

        return modelMapper.map(result, OwnerEntity.class);
    }
}
