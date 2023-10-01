package com.jhonny.products.payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayloadIn implements Serializable {
	
	private static final long serialVersionUID = -4598247418482731448L;
	
	private String orderBy;
	private String orderDirection;
	private String algorithm;
	
}
