package modernjava;

import static java.util.stream.Collectors.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import modernjava.product.Product;

public class StreamMain {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Integer conditionValue = 20;
		
		Integer result = null;
		for (Integer number : numbers) {
			if (number > 3 && number < 9) {
				Integer newNumber = number * 2;
				if (newNumber > conditionValue) {
					result = newNumber;
					break;
				}
			}
		}
		System.out.println("Imperative Result : " + result);
		
		Optional<Integer> optResult = numbers.stream()
			   .filter(number -> number > 3 && number < 9)
			   .map(number -> number * 2)
			   .filter(number -> number > conditionValue)
			   .findFirst();
		if (optResult.isPresent()) result = optResult.get();
		System.out.println("Functional Result : " + optResult + " / " + result);
		
		//-----------------------------------------------------------------------------
		// Stream.of
		//-----------------------------------------------------------------------------
		System.out.println( "To List : " +
			Stream.of(1,5,2,3,3,5)
				  .map(number -> number * 2)
				  .sorted(Comparator.naturalOrder())
				  .collect(toList())); 
		
		System.out.println( "To Set : " +
			Stream.of(1,5,2,3,3,5)
				  .map(number -> number * 2)
				  .sorted(Comparator.naturalOrder())
				  .collect(toSet())); 
		
		System.out.println( "Joining : " +
			Stream.of(1,5,2,3,3,5)
				  .sorted(Comparator.reverseOrder())
				  .map(number -> String.valueOf(number * 2))
				  .collect(joining(", ", "(( ", " ))"))); 
		
		System.out.println( "Joining distinct : " +
			Stream.of(1,5,2,3,3,5)
				  .sorted(Comparator.reverseOrder())
				  .map(number -> String.valueOf(number * 2))
				  .distinct()
				  .collect(joining(", ", "{ ", " }"))); 
		
		//-----------------------------------------------------------------------------
		// Integer 127까지는 Cashed 값 사용 
		//-----------------------------------------------------------------------------
		final Integer integer127 = 127;
		System.out.println("integer127 : " + Stream.of(1,2,3,4,127).filter(number -> number == integer127).findFirst());
		final Integer integer128 = 128;
		System.out.println("integer128 : " + Stream.of(1,2,3,4,128).filter(number -> number == integer128).findFirst());
		  
		//-----------------------------------------------------------------------------
		// Product
		//-----------------------------------------------------------------------------	
		System.out.println("-----------------------------------------------------------------------------");
		List<Product> products = Arrays.asList(
				new Product(1L, "A", new BigDecimal("100.50")),
				new Product(2L, "B", new BigDecimal("23.00")),
				new Product(3L, "C", new BigDecimal("31.45")),
				new Product(4L, "D", new BigDecimal("80.20")),
				new Product(5L, "E", new BigDecimal("7.50"))
			);
		
		System.out.println( "Product.price >= 30 : " + 
			products.stream()
					.filter(product -> product.getPrice().compareTo(new BigDecimal("30")) > 0)
					.collect(toList())
		);
		
		System.out.println( "Product.price >= 30 (with joining) : \n" +
			products.stream()
					.filter(product -> product.getPrice().compareTo(new BigDecimal("30")) > 0)
					.map(product -> product.toString())
					.collect(joining("\n"))
		);
		
		System.out.println("Total 구하는 방법");
		System.out.println("IntStream sum() : " + 
			IntStream.of(1,2,3,4,5).sum()
		);
		
		System.out.println( "Total Price reduce() : " + 
			products.stream()
					.map(product -> product.getPrice())
					.reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
		);
		
		System.out.println( "Count : " +
			products.stream()
					.filter(product -> product.getPrice().compareTo(new BigDecimal("30")) > 0)
					.count()
		);
		
	}
}
