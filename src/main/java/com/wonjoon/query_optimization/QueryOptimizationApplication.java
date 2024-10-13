package com.wonjoon.query_optimization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QueryOptimizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryOptimizationApplication.class, args);
	}
}
