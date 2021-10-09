package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SampleMain {

	public static void main(String[] args) {
		
		// -------------------------------------------------------------
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		String numberToString = numbers.stream()
				.map(number -> String.valueOf(number))
				.collect(Collectors.joining(" : "));
		
		String numberToString2 = numbers.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(" : "));
		
		System.out.println(numberToString);
		System.out.println(numberToString2);
		
		// -------------------------------------------------------------
		BiFunction<Double, Double, Double> addFunc = (num1, num2) -> num1 + num2;
		BiFunction<Double, Double, Double> subFunc = (num1, num2) -> num1 - num2;
		BiFunction<Double, Double, Double> multiFunc = (num1, num2) -> num1 * num2;
		BiFunction<Double, Double, Double> divideFunc = (num1, num2) -> num1 / num2;
		
		System.out.println("더하기 : " + Calculate.calculate(addFunc, 1d, 2d)); 
		System.out.println("빼기 : " + Calculate.calculate(subFunc, 1d, 2d)); 
		System.out.println("곱하기 : " + Calculate.calculate(multiFunc, 1d, 2d)); 
		System.out.println("나누기 : " + Calculate.calculate(divideFunc, 1d, 2d)); 

		// -------------------------------------------------------------
		List<Integer> pNumbers = Arrays.asList(-1,-2,-3,-4,-5,-6,-7,-8,-9,-10, 0, 1,2,3,4,5,6,7,8,9,10);
		System.out.println(getFilteredList(pNumbers, number -> number > 0));
		System.out.println(getFilteredList(pNumbers, number -> number < 3));
		
		List<String> strArrays = Arrays.asList("Test","Interest","Pickup","Coffee","TV","Digital");
		System.out.println(getFilteredList(strArrays, str -> str.toUpperCase().indexOf("T") > -1)); 
		System.out.println(getFilteredList(strArrays, str -> str.toUpperCase().indexOf("O") > -1)); 
		
		// -------------------------------------------------------------
		long startTime = System.currentTimeMillis();
		print(1, () -> getTextSupplier());
		print(2, () -> getTextSupplier());
		print(-1, () -> getTextSupplier());
		long endTime = System.currentTimeMillis();
		
		System.out.println("It took " + (endTime - startTime)/1000 + " Seconds.");
		
	}
	
	private static <T> List<T> getFilteredList(List<T> list, Predicate<T> predicate) {
		List<T> result = new ArrayList<T>();
		for (T item : list) {
			if (predicate.test(item)) result.add(item);
		}
		return result;
	}
	
	private static <T> void print(int testNum, Supplier<T> supplier) {
		if (testNum >= 0) System.out.println("Success : " + supplier.get());
		else System.out.println("Fail!");
	}

	private static String getTextSupplier() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Excute getTextSupplier()";
	}
}

class Calculate {
	public static Double calculate(BiFunction<Double, Double, Double> function, Double num1, Double num2) {
		return function.apply(num1, num2);
	}
}