package com.cgy.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // For redis caching
// @MapperScan(basePackages = "com.cgy.sell.dataobject.mapper")    // Config for mybatis mapper (not in use)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
