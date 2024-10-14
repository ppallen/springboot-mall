package com.allen.springbootmall.controller;


import com.allen.springbootmall.constant.ProductCategory;
import com.allen.springbootmall.dto.ProdcutQueryParams;
import com.allen.springbootmall.dto.ProductRequest;
import com.allen.springbootmall.model.Product;
import com.allen.springbootmall.service.ProductService;
import com.allen.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //所有商品列表，條件查詢
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy, //根據欄位排序
            @RequestParam(defaultValue = "desc") String sort, //升(asc)或降(desc)序

            //分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit, //取得幾筆數據->SQL LIMIT
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //跳過多少數據->SQL OFFSET
    ) {
        //設置參數
        ProdcutQueryParams prodcutQueryParams = new ProdcutQueryParams();
        prodcutQueryParams.setCategory(category);
        prodcutQueryParams.setSearch(search);
        prodcutQueryParams.setOrderBy(orderBy);
        prodcutQueryParams.setSort(sort);
        prodcutQueryParams.setLimit(limit);
        prodcutQueryParams.setOffset(offset);

        //取得商品列表數據
        List<Product> productList = productService.getProducts(prodcutQueryParams);

        //取得products總數
        Integer total = productService.countProducts(prodcutQueryParams);

        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);


        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //更新 product
        productService.updataProduct(productId,productRequest);
        Product updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
