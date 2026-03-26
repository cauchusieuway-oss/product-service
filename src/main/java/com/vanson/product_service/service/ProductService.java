package com.vanson.product_service.service;

import com.vanson.product_service.client.ShopClient;
import com.vanson.product_service.dto.ShopDto;
import com.vanson.product_service.entity.Product;
import com.vanson.product_service.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopClient shopClient;

    public ProductService(ProductRepository productRepository, ShopClient shopClient) {
        this.productRepository = productRepository;
        this.shopClient = shopClient;
    }

    private Long getShopIdByEmail(String email) {

        ShopDto shop = shopClient.getMyShop(email);
        return shop.getId();
    }

    // 🔥 CREATE PRODUCT (KHÔNG CHECK USER Ở ĐÂY)
    @CacheEvict(value = "products", key = "#shopId")
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

//    // 🔥 GET PRODUCT THEO SHOP
//    @Cacheable(value = "products", key = "#shopId")
//    public List<Product> getProductsByShop(Long shopId) {
//        return productRepository.findByShopId(shopId);
//    }

    public List<Product> getProductsByUser(String email) {
        // giả lập mapping user -> shop
        Long shopId = getShopIdByEmail(email);

        return productRepository.findByShopId(shopId);
    }
}