package modernjava.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import modernjava.cal.CustomFunctionInterface;

public class FunctionalMain {

	public static void main(String[] args) {

		// 1. Function
		Function<Integer, Integer> function = i -> i*2;
		int result = function.apply(2);
		System.out.println(result);
		
		// 2. Consumer
		Consumer<String> consumer = str -> System.out.println("Hello " + str + "!");
		consumer.accept("Lee sy");
		
		// 3. Predicate
		Predicate<Integer> predicate = i -> i > 1;
		List<Integer> aList = Arrays.asList(-5,-4,-3,-2,-1, 0, 1,2,3,4,5);
		List<Integer> newList = new ArrayList<>();
		for (int num : aList) {
			if(predicate.test(num)) newList.add(num);
		}
		System.out.println(newList);
		
		// 4. Supplier
		// number가 0보다 큰경우만 supplier value를 가지고 와야 하므로 supplier를 사용한다.
		Supplier<String> supplier = () -> getSupplierValue();
		long start = System.currentTimeMillis();
		printIfValidIndex(0, supplier);
		printIfValidIndex(1, supplier);
		printIfValidIndex(-1, supplier);
		System.out.println("It took " + String.valueOf((System.currentTimeMillis() - start) / 1000) + "seconds.");
		
		// 5. CustomFunctionInterface
		System.out.println("CustomFunctionInterface : " + 
				customFunction("The area", 3, 3, (message, width, height) -> message + " is " + String.valueOf(width * height) + "㎡" ) );
	}
	
	private static String getSupplierValue() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Supplier";
	}
	
	private static void printIfValidIndex(int number, Supplier<String> supplier) {
		if (number > 0) {
			System.out.println("The value is " + supplier.get() + ".");
		} else {
			System.out.println("Invalid value!!");
		}
	}
	
	private static <T1, T2, T3> String customFunction(T1 t1, T2 t2, T3 t3, CustomFunctionInterface<T1, T2, T3, String> custFunc ) {
		return custFunc.apply(t1, t2, t3);
	}
}




