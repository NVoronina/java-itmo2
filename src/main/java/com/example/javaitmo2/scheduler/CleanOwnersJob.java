//package com.example.javaitmo2.scheduler;
//
//import com.example.javaitmo2.entity.OwnerEntity;
//import com.example.javaitmo2.repository.OwnerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class CleanOwnersJob {
//
//    @Autowired
//    private OwnerRepository ownerRepository;
//
//    //10 seconds
//    @Scheduled(fixedRate = 30000)
//    public void reportCurrentData() {
//        System.out.println("Scheduler start working");
//        List<OwnerEntity> listOwners = ownerRepository.getNotRelatedOwners();
//        System.out.println("Scheduler to delete count " + listOwners.size());
//        ownerRepository.deleteAll(listOwners);
//        System.out.println("Scheduler end working");
//    }
//}
