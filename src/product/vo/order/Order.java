package product.vo.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Order {
	private Long id;
	private String orderNo;
	private List<OrderedItem> items;
}
