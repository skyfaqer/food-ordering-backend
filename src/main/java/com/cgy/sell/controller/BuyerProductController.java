package com.cgy.sell.controller;

import com.cgy.sell.VO.CategoryVO;
import com.cgy.sell.VO.ProductVO;
import com.cgy.sell.VO.ResultVO;
import com.cgy.sell.dataobject.ProductCategory;
import com.cgy.sell.dataobject.ProductInfo;
import com.cgy.sell.service.CategoryService;
import com.cgy.sell.service.ProductService;
import com.cgy.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Find all for-sale products for buyers
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "1") // Set cache when requesting product info for the first time
    public ResultVO list() {
        // Find all for-sale products
        List<ProductInfo> productInfoList = productService.findUpAll();

        // Find products' categories in one time
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // Generate data
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryType(productCategory.getCategoryType());
            categoryVO.setCategoryName(productCategory.getCategoryName());
            List<ProductVO> productVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(productInfo, productVO);
                    productVOList.add(productVO);
                }
            }
            categoryVO.setProductVOList(productVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultVOUtil.success(categoryVOList);
    }
}
