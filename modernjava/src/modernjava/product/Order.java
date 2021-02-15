package modernjava.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import modernjava.ProductMain;

@AllArgsConstructor
@Data
public class Order {
	private Long id;
	private String orderNumber;
	private List<OrderedItem> orderedItems;
	
	public BigDecimal total() {
		BigDecimal total = BigDecimal.ZERO;
//		for (OrderedItem orderedItem : getOderedItems()) {
//			total = total.add(orderedItem.orderdPrice());
//		}
		
		total = ProductMain.getTotal(this.orderedItems, orderedItem -> orderedItem.orderdPrice());
		return total;
	}
	
	public BigDecimal reduceTotal( ) {
		return this.orderedItems.stream()
						 .map(item -> item.orderdPrice())
//						 .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
						 .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
