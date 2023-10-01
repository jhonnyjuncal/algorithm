package com.jhonny.products.model;

import java.util.Map;

import lombok.Data;

@Data
public class MetricComparable implements Comparable<MetricComparable> {
	
	static final String COMPARE_BY_NAME = "name";
	static final String COMPARE_BY_SALES_UNITS = "sales_units";
	static final String COMPARE_BY_STOCK = "stock";
	
	private Integer id;
	private String name;
	private Integer salesUnit;
	private Map<String, Double> stock;
	private String comparableField;
	
	
	public Double getMaxStock() {
		return stock.values().stream().mapToDouble(v -> v).max().getAsDouble();
	}
	
	
	@Override
	public int compareTo(MetricComparable o) {
		switch (comparableField) {
			case COMPARE_BY_NAME:
				return this.name.compareTo(o.getName());
				
			case COMPARE_BY_SALES_UNITS:
				if (this.salesUnit > o.getSalesUnit()) {
		            return 1;
		        } else if (this.salesUnit < o.getSalesUnit()) {
		            return -1;
		        } else {
		            return 0;
		        }
				
			case COMPARE_BY_STOCK:
				if (this.stock.values().stream().mapToDouble(v -> v).max().getAsDouble() > 
						o.getStock().values().stream().mapToDouble(v -> v).max().getAsDouble()) {
		            return 1;
		        } else if (this.stock.values().stream().mapToDouble(v -> v).max().getAsDouble() < 
		        		o.getStock().values().stream().mapToDouble(v -> v).max().getAsDouble()) {
		            return -1;
		        } else {
		            return 0;
		        }
		}
		return 0;
	}
}
