package com.jhonny.products.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhonny.products.entity.Metric;

@Repository
public interface ProductsRepository extends CrudRepository<Metric, Long> {
	
	List<Metric> findAll();
}
