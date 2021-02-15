package modernjava;

import static java.util.stream.Collectors.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;
import modernjava.product.Product;

public class MethodReferenceMain {
	public static void main(String[] args) {
		System.out.println(
		Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
			  .stream()
//			  .sorted((bd1, bd2) -> bd1.compareTo(bd2))
			  .sorted(BigDecimal::compareTo)
			  .collect(toList())
		);
		
		final String targetString = "c";
		System.out.println(
		Arrays.asList("a", "b", "c", "d", "e")
			  .stream()
			  .anyMatch(targetString::equals)
		);
		
		methodReference("Lambda Expression", 3, number -> String.valueOf(number * 2));
		methodReference("Method Reference", 3, MethodReferenceMain::doubleThenToString);
		methodReference("Method Reference Use Function", 3, getMethodReferenceFunction());
		System.out.println(getMethodReferenceFunction().apply(4));
		
		List<Function<Integer, String>> functionList = Arrays.asList(
				number -> String.valueOf(number * 2), 
				MethodReferenceMain::doubleThenToString,
				MethodReferenceMain::addHashTag,
				addHashTagFunction());
		
		System.out.println("------------------[Start] Function List-------------------------");
		for (Function<Integer, String> function : functionList) {
			System.out.println(function.apply(10));
		}
		System.out.println("------------------[End]   Function List-------------------------");
		System.out.println("------------------[Start] Constructor  -------------------------");
		Section section = new Section(1);
		Function<Integer, Section> sectionLambdaExpress = number -> new Section(number);
		Section sectionLamda = sectionLambdaExpress.apply(1);
		Function<Integer, Section> sectionMethodReference = Section::new;
		Section sectionMethod = sectionMethodReference.apply(1);
		System.out.println("section       : " + section);
		System.out.println("sectionLamda  : " + sectionLamda);
		System.out.println("sectionMethod : " + sectionMethod);
		
		Product product = new Product(1L, "A", new BigDecimal("100"));
		System.out.println("product       : " + product);
		
		ProductCreator productCreate = Product::new;
		System.out.println("productCreate : " + productCreate.create(1L, "A", new BigDecimal("100")));
		
		ProductA prdA = createProduct(1L, "A", new BigDecimal("100"), ProductA::new);
		ProductB prdB = createProduct(2L, "B", new BigDecimal("200"), ProductB::new);
		ProductC prdC = createProduct(3L, "C", new BigDecimal("250"), ProductC::new);
		System.out.println(prdA);
		System.out.println(prdB);
		System.out.println(prdC);
	}
	
	private static <T extends Product> T createProduct( Long id, 
														String name, 
														BigDecimal price,
														TProductCreator<T> productCreator) {
		return productCreator.create(id, name, price);
	}
	
	private static String doubleThenToString(int number) {
		return String.valueOf(number * 2);
	}
	
	private static String addHashTag(int number) {
		return "#" + String.valueOf(number);
	}
	
	private static void methodReference(String name, int number, Function<Integer, String> f) {
		System.out.println("[" + name + "] The value is " + f.apply(number));
	}
	
	private static Function<Integer, String> getMethodReferenceFunction() {
		return MethodReferenceMain::doubleThenToString;
	}
	
	private static Function<Integer, String> addHashTagFunction() {
		return MethodReferenceMain::addHashTag;
	}
}

@AllArgsConstructor
@Data
class Section{
	private int number;
}

@FunctionalInterface
interface ProductCreator {
	Product create(Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface TProductCreator<T extends Product> {
	T create(Long id, String name, BigDecimal price);
}

class ProductA extends Product {
	public ProductA(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
	
	@Override
	public String toString() {
		return "A=" + super.toString();
	}
}

class ProductB extends Product {
	public ProductB(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
	
	@Override
	public String toString() {
		return "B=" + super.toString();
	}
}

class ProductC extends Product {
	public ProductC(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
	
	@Override
	public String toString() {
		return "C=" + super.toString();
	}
}
