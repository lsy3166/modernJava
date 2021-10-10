package product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import product.vo.order.Order;
import product.vo.order.OrderedItem;
import product.vo.product.DiscountedProduct;
import product.vo.product.Product;

public class FunctionalExamples {
	public static void main(String[] args) {
		Product product1 = new Product(1L, "A", new BigDecimal("10.00"));
		Product product2 = new Product(2L, "B", new BigDecimal("17.00"));
		Product product3 = new Product(3L, "C", new BigDecimal("20.00"));
		Product product4 = new Product(4L, "D", new BigDecimal("37.50"));
		Product product5 = new Product(5L, "E", new BigDecimal("55.00"));
		Product product6 = new Product(6L, "F", new BigDecimal("125.60"));
		Product product7 = new Product(7L, "G", new BigDecimal("17.00"));
		Product product8 = new Product(8L, "H", new BigDecimal("55.00"));
		Product product9 = new Product(9L, "I", new BigDecimal("10.00"));
		List<Product> products = Arrays.asList(
			product1
			,product2
			,product3
			,product4
			,product5
			,product6
			,product7
			,product8
			,product9
		);
		
		Set<BigDecimal> sProducts = products.stream()
										.map(product -> product.getPrice())
										.collect(Collectors.toSet());
		
		List<BigDecimal> lProducts = products.stream()
										.map(product -> product.getPrice())
										.collect(Collectors.toList());
		
		for (BigDecimal price : sProducts) {
			lProducts.remove(price);
		}
		Set<Product> newProducts = new HashSet<Product>();
		for (BigDecimal price : lProducts) {
			newProducts.add(products.stream().filter(product -> product.getPrice().equals(price)).findFirst().get());
		}
		System.out.println(
			"중복 요소 : " + newProducts
		);
		
		System.out.println(
				filteredPrice(products, product -> product.getPrice().compareTo(new BigDecimal("20")) >= 0)
		);
		System.out.println(
				filteredPrice(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0)
		);
		
		List<DiscountedProduct> discountedPrds = products.stream()
				.filter(product -> product.getPrice().compareTo(new BigDecimal("50"))>=0)
				.map(product -> new DiscountedProduct(product.getId()
													, product.getName()
													, product.getPrice().multiply(new BigDecimal("0.5"))))
				.collect(Collectors.toList());
		
		System.out.println(discountedPrds);
		
		Predicate<Product> moreThan30Price = product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0;
		System.out.println(filteredPrice(products, moreThan30Price));
		System.out.println(filteredPrice(discountedPrds, moreThan30Price));
		
		List<Object> pList = priceList(products, prd -> prd.getPrice());
		System.out.println(pList);
		System.out.println(total(pList));
		
		List<Object> pDList = priceList(discountedPrds, dPrd -> dPrd.getPrice());
		System.out.println(pDList);
		System.out.println(total(pDList));
		
		System.out.println("==================================【 Order 】==============================================");
		
		OrderedItem item1 = new OrderedItem(1L, product1, 1);
		OrderedItem item2 = new OrderedItem(2L, product2, 2);
		OrderedItem item3 = new OrderedItem(3L, product3, 3);
		OrderedItem item4 = new OrderedItem(4L, product4, 4);
		OrderedItem item5 = new OrderedItem(5L, product5, 5);
		OrderedItem item6 = new OrderedItem(6L, product6, 10);
		
		Order order = new Order(1L, "ORD01", Arrays.asList(item1, item2, item3));
		System.out.println(order);
		List<Object> orderedItems = priceList(order.getItems(), orderedItem -> orderedItem.totalPrice());
		System.out.println(order.getOrderNo() + " TOTAL : " + total(orderedItems));
		
		Order order2 = new Order(2L, "ORD02", Arrays.asList(item4, item5, item6));
		System.out.println(order2);
		List<Object> orderedItems2 = priceList(order2.getItems(), orderedItem -> orderedItem.totalPrice());
		System.out.println(order2.getOrderNo() + " TOTAL : " + total(orderedItems2));
		
	}
	
	private static <T> List<T> filteredPrice(List<T> list, Predicate<? super T> predicate) {
		return list.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}
	
	private static <T> List<Object> priceList(List<T> list, Function<T, Object> function) {
		List<Object> result = new ArrayList<Object>(); 
		
		for (T t : list) {
			result.add(function.apply(t));
		}
		
		return result;
	}
	
	private static BigDecimal total(List<Object> list) {
		return list.stream().map(item -> (BigDecimal) item).reduce(BigDecimal::add).get();
	}
}


