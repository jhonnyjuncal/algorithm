package com.jhonny.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhonny.products.algorithm.MergeSortAlgorithm;
import com.jhonny.products.algorithm.RatioCorvertion;
import com.jhonny.products.entity.Metric;
import com.jhonny.products.mapper.ProductsMapper;
import com.jhonny.products.model.MetricComparable;
import com.jhonny.products.model.ProductsOrder;
import com.jhonny.products.repository.ProductsRepository;

@Service
public class ProductsService {
	
	private final ProductsRepository productsRepository;
	
	
	public ProductsService(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}
	
	public List<MetricComparable> getMetricsOrderByAlgorithm(ProductsOrder productsOrder) {
		List<MetricComparable> withoutOrderList = new ArrayList<>();
		
		// get product list from database
		List<Metric> productList = productsRepository.findAll();
		
		// calculate ratios for stocks values
		productList.stream().forEach(metric -> {
			MetricComparable metricBussines = ProductsMapper.INSTANCE.metricComparableToMetric(metric);
			metricBussines.setStock(RatioCorvertion.convertRatio(metric.getStock()));
			withoutOrderList.add(metricBussines);
		});
		
		// call to order methods with parameters
		MergeSortAlgorithm mergeSortAlgorithm = new MergeSortAlgorithm();
		return mergeSortAlgorithm.orderListWithParameters(productsOrder, withoutOrderList);
	}
}
