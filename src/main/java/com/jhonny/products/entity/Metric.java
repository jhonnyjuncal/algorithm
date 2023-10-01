package com.jhonny.products.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "METRICS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metric implements Serializable {
	
	private static final long serialVersionUID = -4598247418482731448L;
	
	@Id
	private Integer id;
	private String name;
	private Integer salesUnit;
	private String stock;
	
	@Override
	public String toString() {
		return "Metric{" + "id=" + this.id + ", name='" + this.name + '\'' + ", sales_unit='" + this.salesUnit + ", stock='" + this.stock + '\'' + '}';
	}
}
