package modernjava.main;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import modernjava.product.DiscountedProduct;
import modernjava.product.Order;
import modernjava.product.OrderedItem;
import modernjava.product.Product;

public class ProductMain {

	public static void main(String[] args) {
		Product prodA = new Product(1L, "A", new BigDecimal("10.00"));
		Product prodB = new Product(2L, "B", new BigDecimal("15.50"));
		Product prodC = new Product(3L, "C", new BigDecimal("124.00"));
		Product prodD = new Product(4L, "D", new BigDecimal("110.99"));
		Product prodE = new Product(5L, "E", new BigDecimal("17.85"));
		Product prodF = new Product(6L, "F", new BigDecimal("32.85"));
		List<Product> products = Arrays.asList(
				prodA
				,prodB
				,prodC
				,prodD
				,prodE
				,prodF
				);
	
		List<Product> newProducts = new ArrayList<>();
		newProducts = products.stream() 
							  .filter(product -> product.getPrice().compareTo(new BigDecimal("20")) > 0)
							  //.sorted((product1, product2) -> product1.getPrice().compareTo(product2.getPrice()))
							  .sorted(comparing(Product::getPrice))
							  .collect(toList());
		System.out.println("** stream filter      : " + newProducts);
		
		Product minProduct = products.stream().min(comparing(Product::getPrice)).get();
		System.out.println("** stream minProduct  : " + minProduct);
		
		Product maxProduct = products.stream().max(comparing(Product::getPrice)).get();
		System.out.println("** stream maxProduct  : " + maxProduct);
		
		
		List<Product> expensiveProducts = mkfilter(products, product -> product.getPrice().compareTo(new BigDecimal("100")) >= 1);
		System.out.println("expensiveProducts        : " + expensiveProducts);

		List<DiscountedProduct> discountedProducts = mkmap(expensiveProducts
				, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
		System.out.println("discountedProducts       : " + discountedProducts);
		
		List<DiscountedProduct> sorteddiscountedProducts = mkmap(expensiveProducts
				, product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
		System.out.println("sorteddiscountedProducts : " + sorteddiscountedProducts.stream().sorted(comparing(DiscountedProduct::getPrice)).collect(toList()));
		
		BigDecimal total = getTotal(products, product -> product.getPrice());
		BigDecimal discoutedProducttotal = getTotal(discountedProducts, product -> product.getPrice());
		System.out.println("price total : " + total);
		System.out.println("discoutedProduct price total : " + discoutedProducttotal);
		
		//-------------------------------------------------------------------------------
		// Ordered Item
		//-------------------------------------------------------------------------------
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		orderedItems.add(new OrderedItem(1L, prodA, 10));
		orderedItems.add(new OrderedItem(2L, prodB, 10));
		orderedItems.add(new OrderedItem(3L, prodC, 10));
		orderedItems.add(new OrderedItem(4L, prodD, 10));
		orderedItems.add(new OrderedItem(5L, prodE, 10));
		orderedItems.add(new OrderedItem(6L, prodF, 10));
		
		Order order = new Order(1L, "Order01", orderedItems);
		System.out.println("Order Items Total : " + order.total());
		
	}

	private static <T> List<T> mkfilter (List<T> list, Predicate<T> predicate) {
		List<T> result = new ArrayList<>();
		for (T t : list) {
			if(predicate.test(t)) result.add(t);
		}
		return result;
	}
	
	private static <T,R> List<R> mkmap(List<T>  list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for ( T t : list ) {
			result.add(function.apply(t));
		}
		return result;
	}
	
	public static <T> BigDecimal getTotal(List<T> list, Function<T, BigDecimal> function) {
		BigDecimal total = BigDecimal.ZERO;
		for ( T t : list ) {
			total = total.add(function.apply(t));
		}
		
		return total;
	}

}
