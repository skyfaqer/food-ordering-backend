package com.cgy.sell.controller;

import com.cgy.sell.dataobject.ProductCategory;
import com.cgy.sell.dataobject.ProductInfo;
import com.cgy.sell.enums.ResultEnum;
import com.cgy.sell.exception.SellException;
import com.cgy.sell.form.ProductForm;
import com.cgy.sell.service.CategoryService;
import com.cgy.sell.service.ProductService;
import com.cgy.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

// Process product info for seller
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Find list of products according to page number and size of each page
    // Page number starting from 0
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        Integer totalPages = productInfoPage.getTotalPages();
        Integer startPage = Math.max(1, page - 5);
        Integer endPage = Math.min(totalPages, page + 5);
        if (endPage - startPage + 1 < 11) {
            if (startPage == 1) {
                endPage = Math.min(startPage + 10, totalPages);
            }
            else if (endPage.equals(totalPages)) {
                startPage = Math.max(endPage - 10, 1);
            }
        }
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        return new ModelAndView("product/list", map);
    }

    // Put a product on sale (enable)
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            map.put("urlName", "product list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ON_SALE.getMessage());
        map.put("url", "/sell/seller/product/list");
        map.put("urlName", "product list");
        return new ModelAndView("common/success", map);
    }

    // Put a product off sale (disable)
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            map.put("urlName", "product list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFF_SALE.getMessage());
        map.put("url", "/sell/seller/product/list");
        map.put("urlName", "product list");
        return new ModelAndView("common/success", map);
    }

    // Add a product or view detail of a product
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // Find all existing categories
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    // Save or update product info
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product", key = "1")    // Remove cache on updating product info
    // @CachePut(cacheNames = "product", key = "123"), only usable when method's return type is the same as the stored type in cache product.123
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/list");
            map.put("urlName", "product list");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            // If productId is not empty, then it is a modified product; else, it is a new product
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.generateUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            map.put("urlName", "product list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "saved successfully");
        map.put("url", "/sell/seller/product/list");
        map.put("urlName", "product list");
        return new ModelAndView("common/success", map);
    }
}
