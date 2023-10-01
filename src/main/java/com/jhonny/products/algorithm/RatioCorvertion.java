package com.jhonny.products.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatioCorvertion {
	
	public static Map<String, Double> convertRatio(String stock) {
		Map<String, Double> results = new HashMap<>();
		
		if (stock != null) {
			List<String> values = Arrays.asList(stock.split("/"));
			Map<String, Double> sizeMap = new HashMap<>();
			
			values.stream().forEach(size -> {
				String[] sizeQuantity = size.split(":");
				Double quantity = Double.valueOf(sizeQuantity[1]);
				if (quantity > 0) {
					sizeMap.put(sizeQuantity[0], quantity);
				}
			});
			
			if (sizeMap != null && sizeMap.size() > 0) {
				double total = sizeMap.values().stream().mapToDouble(Double::doubleValue).sum();
				
				for (String key : sizeMap.keySet()) {
			        Double quantity = sizeMap.get(key);
			        results.put(key.trim(), (quantity / total) * 100);
			    }
			}
		}
		
		return results;
	}
}
