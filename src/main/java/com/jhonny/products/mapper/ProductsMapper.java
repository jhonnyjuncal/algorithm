package com.jhonny.products.mapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.jhonny.products.entity.Metric;
import com.jhonny.products.model.MetricComparable;
import com.jhonny.products.model.ProductsOrder;
import com.jhonny.products.payload.ProductPayloadIn;
import com.jhonny.products.payload.ProductPayloadOut;

@Mapper
public interface ProductsMapper {
	
	ProductsMapper INSTANCE = Mappers.getMapper(ProductsMapper.class);
	static final DecimalFormat decimalFormater = new DecimalFormat("0.00");
	
	
	@Mapping(source = "stock", target = "stock", ignore = true)
	Metric metricToMetricComparable(MetricComparable metricComparable);
	
	@Mapping(source = "stock", target = "stock", ignore = true)
	@Mapping(target = "comparableField", ignore = true)
	MetricComparable metricComparableToMetric(Metric metric);
	
	@Mapping(source = "stock", target = "stock", qualifiedByName = "stockMapToString")
	ProductPayloadOut metricComparablToProductPayloadOut(MetricComparable metricComparable);
	
//	@Mapping(source = "ProductPayloadIn", target = "ProductsOrder", qualifiedByName = "productPayloadInToProductsOrder")
//	ProductsOrder validateProductPayloadIn(ProductPayloadIn productPayloadIn);
	
	
	default ProductsOrder productPayloadInToProductsOrder(ProductPayloadIn productPayloadIn) throws Exception {
		ProductsOrder result = new ProductsOrder();
		
		if (productPayloadIn.getOrderBy() == null || productPayloadIn.getOrderBy().isEmpty()) {
			throw new Exception("Field 'name' cannot be empty");
		} else {
			result.setOrderBy(productPayloadIn.getOrderBy());
		}
		
		if (productPayloadIn.getOrderDirection() == null || productPayloadIn.getOrderDirection().isEmpty()) {
			throw new Exception("Field 'orderDirection' cannot be empty");
		} else {
			result.setOrderDirection(productPayloadIn.getOrderDirection());
		}
		
		if (productPayloadIn.getAlgorithm() == null || productPayloadIn.getAlgorithm().isEmpty()) {
			throw new Exception("Field 'algorithm' cannot be empty");
		} else {
			result.setAlgorithm(productPayloadIn.getAlgorithm());
		}
		
		return result;
	}
	
	default Comparable[] metricComparableListToComparableArray(List<MetricComparable> metricList) {
		Comparable[] comparableList = new Comparable[metricList.size()];
		for (int i=0; i<metricList.size(); i++ ) {
			comparableList[i] = metricList.get(i);
		}
		return comparableList;
	}
	
	default List<MetricComparable> comparableArrayToMetricComparableList(Comparable[] comparableList) {
		List<MetricComparable> metricList = new ArrayList<>();
		for (int i=0; i<comparableList.length; i++ ) {
			metricList.add((MetricComparable)comparableList[i]);
		}
		return metricList;
	}
	
	@Named("stockMapToString")
	public static String stockMapToString(Map<String, Double> stockMap) {
		String stockString = "";
		
		stockString += stockMap.get("S") != null ? "S:" + decimalFormater.format(stockMap.get("S")) + "% / " : "S:0% / ";
		stockString += stockMap.get("M") != null ? "M:" + decimalFormater.format(stockMap.get("M")) + "% / " : "M:0% / ";
		stockString += stockMap.get("L") != null ? "L:" + decimalFormater.format(stockMap.get("L")) + "%" : "L:0%";
		
		return stockString;
	}
}
