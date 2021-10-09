package product.vo.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
	private Long id;
	private String name;
	private BigDecimal price;
}
