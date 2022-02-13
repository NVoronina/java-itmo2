package com.example.javaitmo2.service;

import com.example.javaitmo2.dto.response.OwnerResponse;
import com.example.javaitmo2.entity.OwnerEntity;
import com.example.javaitmo2.repository.NotFoundException;
import com.example.javaitmo2.repository.impl.OwnerRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private OwnerRepositoryImpl ownerRepository;
    private ModelMapper modelMapper;

    public OwnerService(OwnerRepositoryImpl ownerRepository, ModelMapper modelMapper) {
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
    }

    public OwnerResponse getOwnerByVin(String vinNumber) throws NotFoundException {
        OwnerEntity ownerEntity = ownerRepository.getOwnerByCarVin(vinNumber);
        if (ownerEntity == null) {
            throw new NotFoundException("No owner found");
        }
        return modelMapper.map(ownerEntity, OwnerResponse.class);
    }
}
