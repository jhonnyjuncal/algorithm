package com.jhonny.products.payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayloadOut implements Serializable {
	
	private static final long serialVersionUID = -8450511990614903896L;
	
	private Integer id;
	private String name;
	private Integer salesUnit;
	private String stock;
	
	@Override
	public String toString() {
		return "Metric{" + "id=" + this.id + ", name='" + this.name + '\'' + ", sales_unit='" + this.salesUnit + ", stock='" + this.stock + '\'' + '}';
	}
}
