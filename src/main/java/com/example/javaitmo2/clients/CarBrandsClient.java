package com.example.javaitmo2.clients;

import com.example.javaitmo2.dto.response.BrandResponse;
import lombok.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "BrandsList", url = "${api_brands}")
public interface CarBrandsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/brands")
    List<BrandResponse> getBrands();

}
