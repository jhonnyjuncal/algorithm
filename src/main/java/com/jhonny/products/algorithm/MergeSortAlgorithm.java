package com.jhonny.products.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.jhonny.products.mapper.ProductsMapper;
import com.jhonny.products.model.MetricComparable;
import com.jhonny.products.model.ProductsOrder;

public class MergeSortAlgorithm {
	
	static final String ALGORITHM_COMPARATOR = "Comparator";
	static final String ALGORITHM_MERGESORT = "MergeSort";
	
	
	public List<MetricComparable> orderListWithParameters(ProductsOrder productsOrder, List<MetricComparable> withoutOrderList) {
		List<MetricComparable> compareMetrics = new ArrayList<>();
		
		if (ALGORITHM_COMPARATOR.equals(productsOrder.getAlgorithm())) {
			// Using Comparator class in Java
			
			switch (productsOrder.getOrderBy()) {
				case "name":
					compareMetrics = sortMetricListByName(withoutOrderList, productsOrder.getOrderDirection());
					break;
					
				case "sales_units":
					compareMetrics = sortMetricListBySalesUnit(withoutOrderList, productsOrder.getOrderDirection());
					break;
					
				case "stock":
					compareMetrics = sortMetricListByStock(withoutOrderList, productsOrder.getOrderDirection());
					break;
			}
			
		} else if (ALGORITHM_MERGESORT.equals(productsOrder.getAlgorithm())) {
			// Using Merge Algorithm
			withoutOrderList.stream().forEach(val -> val.setComparableField(productsOrder.getOrderBy()));
			
			Comparable[] comparableList = ProductsMapper.INSTANCE.metricComparableListToComparableArray(withoutOrderList);
			Comparable[] mergeSortList = mergeSort(comparableList);
			compareMetrics = ProductsMapper.INSTANCE.comparableArrayToMetricComparableList(mergeSortList);
		}
		
		return compareMetrics;
	}
	
	
	private List<MetricComparable> sortMetricListBySalesUnit(List<MetricComparable> withoutOrderList, String orderDirection) {
		Comparator<MetricComparable> comparator =  Comparator.comparing(MetricComparable::getSalesUnit);
		return orderList(withoutOrderList, comparator, orderDirection);
	}
	
	private List<MetricComparable> sortMetricListByName(List<MetricComparable> withoutOrderList, String orderDirection) {
		Comparator<MetricComparable> comparator =  Comparator.comparing(MetricComparable::getName);
		return orderList(withoutOrderList, comparator, orderDirection);
	}
	
	private List<MetricComparable> sortMetricListByStock(List<MetricComparable> withoutOrderList, String orderDirection) {
		Comparator<MetricComparable> comparator =  Comparator.comparing(MetricComparable::getMaxStock);
		return orderList(withoutOrderList, comparator, orderDirection);
	}
	
	private Comparable[] mergeSort(Comparable[] inputList) {
        if (inputList.length <= 1) {
            return inputList;
        }
        
        Comparable[] list1 = new Comparable[inputList.length/2];
        Comparable[] list2 = new Comparable[inputList.length - list1.length];
        System.arraycopy(inputList, 0, list1, 0, list1.length);
        System.arraycopy(inputList, list1.length, list2, 0, list2.length);
        mergeSort(list1);
        mergeSort(list2);
        merge(list1, list2, inputList);
        
        return inputList;
    }
	
	
    private void merge(Comparable[] list1, Comparable[] list2, Comparable[] resultList) {
        int indexOfList1 = 0;
        int indexOfList2 = 0;
        int indexOfMergedList = 0;
        
        while (indexOfList1 < list1.length && indexOfList2 < list2.length) {
            if (list1[indexOfList1].compareTo(list2[indexOfList2]) < 0) {
                resultList[indexOfMergedList] = list1[indexOfList1];
                indexOfList1++;
            } else {
                resultList[indexOfMergedList] = list2[indexOfList2];
                indexOfList2++;
            }
            indexOfMergedList++;
        }
        System.arraycopy(list1, indexOfList1, resultList, indexOfMergedList, list1.length - indexOfList1);
        System.arraycopy(list2, indexOfList2, resultList, indexOfMergedList, list2.length - indexOfList2);
    }
    
    
    private List<MetricComparable> orderList(List<MetricComparable> withoutOrderList, Comparator<MetricComparable> comparator, 
    		String orderDirection) {
    	
    	if ("DESC".equals(orderDirection)) {
			comparator = comparator.reversed();
		}
		withoutOrderList.sort(comparator);
		
		return withoutOrderList;
    }
}
