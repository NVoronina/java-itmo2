package com.example.javaitmo2.repository.impl;

import com.example.javaitmo2.entity.OwnerEntity;

import java.util.List;

public interface OwnerInterface {
    OwnerEntity getOwnerByCarVin(String vin);
    List<OwnerEntity> getNotRelatedOwners();
}
