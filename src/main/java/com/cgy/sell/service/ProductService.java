package com.cgy.sell.service;

import com.cgy.sell.dataobject.ProductInfo;
import com.cgy.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    // Find a list of all for-sale products
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // Increase product stock
    void increaseStock(List<CartDTO> cartDTOList);

    // Decrease product stock
    void decreaseStock(List<CartDTO> cartDTOList);

    // Enable (on sale)
    ProductInfo onSale(String productId);

    // Disable (off sale)
    ProductInfo offSale(String productId);
}
