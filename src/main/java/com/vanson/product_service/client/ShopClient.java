package com.vanson.product_service.client;

import com.vanson.product_service.dto.ShopDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shop-service", url = "https://shop-service-jh80.onrender.com")
public interface ShopClient {

    @GetMapping("/api/shops/my-shop")
    ShopDto getMyShop(@RequestParam String email);
}
