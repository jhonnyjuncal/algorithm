package com.jhonny.products.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhonny.products.mapper.ProductsMapper;
import com.jhonny.products.model.MetricComparable;
import com.jhonny.products.model.ProductsOrder;
import com.jhonny.products.payload.ProductPayloadIn;
import com.jhonny.products.payload.ProductPayloadOut;
import com.jhonny.products.service.ProductsService;

@RequestMapping("/products/")
@RestController
public class ProductsController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ProductsService productsService;
	
	
	public ProductsController(ProductsService productsService) {
		this.productsService = productsService;
	}
	
	
	@PostMapping("v1/list")
	public ResponseEntity<List<ProductPayloadOut>> orderProductUsingAlgorithm(@RequestBody ProductPayloadIn payload) {
		List<ProductPayloadOut> orderedProductsList = null;
		
		try {
			ProductsOrder productsOrder = ProductsMapper.INSTANCE.productPayloadInToProductsOrder(payload);
			
			List<MetricComparable> metricsOrderByAlgorithm2 = productsService.getMetricsOrderByAlgorithm(productsOrder);
			log.info("Products list obtained");
			
			orderedProductsList = metricsOrderByAlgorithm2.stream()
				.map(v -> ProductsMapper.INSTANCE.metricComparablToProductPayloadOut(v))
				.collect(Collectors.toList());
			
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(orderedProductsList);
	}
	
	
}
