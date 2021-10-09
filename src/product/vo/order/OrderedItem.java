package product.vo.order;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import product.vo.product.Product;

@AllArgsConstructor
@Data
public class OrderedItem {
	private Long id;
	private Product product;
	private int quantity;
	
	public BigDecimal totalPrice() {
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
}
