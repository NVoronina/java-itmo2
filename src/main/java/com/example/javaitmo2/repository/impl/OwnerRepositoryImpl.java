package com.example.javaitmo2.repository.impl;

import com.example.javaitmo2.entity.OwnerEntity;
import com.example.javaitmo2.repository.impl.OwnerInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerInterface {

    @Autowired
    private EntityManager em;

    @Autowired
    private ModelMapper modelMapper;

    public OwnerEntity getOwnerByCarVin(String vinNumber) {
        Object result = this.em
                .createNativeQuery(
                        "SELECT o.* FROM owners o " +
                                "INNER JOIN cars c ON o.id = c.owner_id " +
                                "WHERE c.vin_number = :vin", OwnerEntity.class)
                .setParameter("vin", vinNumber)
                .getSingleResult();

        return modelMapper.map(result, OwnerEntity.class);
    }

    public List<OwnerEntity> getNotRelatedOwners() {
        Query query =  this.em
                .createNativeQuery(
                        "SELECT o.* FROM owners o " +
                                "LEFT JOIN cars c on o.id = c.owner_id " +
                                "LEFT JOIN users u on o.user_id = u.id " +
                                "WHERE u.id IS NULL AND c.id IS NULL;", OwnerEntity.class);
        List<OwnerEntity> result = (List) query.getResultList();

        return result;
    }
}
