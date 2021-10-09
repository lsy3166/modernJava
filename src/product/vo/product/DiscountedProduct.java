package product.vo.product;

import java.math.BigDecimal;

import lombok.ToString;

@ToString(callSuper = true)
public class DiscountedProduct extends Product {

	public DiscountedProduct(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}

}
