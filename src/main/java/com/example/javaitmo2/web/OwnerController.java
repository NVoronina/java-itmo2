package com.example.javaitmo2.web;

import com.example.javaitmo2.dto.response.CarResponse;
import com.example.javaitmo2.dto.response.OwnerResponse;
import com.example.javaitmo2.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("owners")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/search")
    public ResponseEntity<OwnerResponse> searchOwners(@RequestParam String vin) {
        return ResponseEntity.ok().body(ownerService.getOwnerByVin(vin));
    }

}
