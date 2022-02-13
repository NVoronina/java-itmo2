//package com.example.javaitmo2.scheduler;
//
//import com.example.javaitmo2.clients.CarBrandsClient;
//import com.example.javaitmo2.dto.response.BrandResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class CheckBrandsJob {
//
//    @Autowired
//    private CarBrandsClient brandClient;
//
//    @Scheduled(fixedRate = 6000000)
//    public void reportCurrentData() {
//        System.out.println("Scheduler start working");
//        List<BrandResponse> brands = brandClient.getBrands();
//        System.out.println(brands.size());
//        System.out.println("Scheduler end working");
//    }
//}
