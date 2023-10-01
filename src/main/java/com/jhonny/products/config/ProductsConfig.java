package com.jhonny.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhonny.products.repository.ProductsRepository;
import com.jhonny.products.service.ProductsService;

@Configuration
public class ProductsConfig {
	
	
	@Bean
	ProductsService productsService(ProductsRepository productsRepository) {
		return new ProductsService(productsRepository);
	}
	
	
}
