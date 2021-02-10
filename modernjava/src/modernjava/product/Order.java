package modernjava.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import modernjava.main.ProductMain;

@AllArgsConstructor
@Data
public class Order {
	private Long id;
	private String orderNumber;
	private List<OrderedItem> oderedItems;
	
	public BigDecimal total() {
		BigDecimal total = BigDecimal.ZERO;
//		for (OrderedItem orderedItem : getOderedItems()) {
//			total = total.add(orderedItem.orderdPrice());
//		}
		
		total = ProductMain.getTotal(this.oderedItems, orderedItem -> orderedItem.orderdPrice());
		return total;
	}
}
