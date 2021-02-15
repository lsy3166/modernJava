package modernjava;

import java.util.function.Function;

public class HigherOrderFunctionMain {

	public static void main(String[] args) {
		// Higher-order Function (고계함수, 고차함수) 
		Function<Function<Integer, String>, String> function = i -> i.apply(10);
		System.out.println( "Function 인수 : " +
			function.apply(i -> "#" + i)
		);
		
		Function<Integer, Function<Integer, String>> function2 = i -> i2 -> "#" + String.valueOf(i + i2);
		System.out.println( "Function 반환 : " +
			function2.apply(1).apply(9)
		);
 		
		Function<Integer, Function<Integer, Function<Integer, Integer>>> function3 = i -> i2 -> i3 -> i + i2+ i3;
		System.out.println( "Function 반환 함수 multi  : " + 
			function3.apply(1).apply(2).apply(3)
		);
		
		Function<Integer, Function<Integer, Integer>> plus10 = function3.apply(10);
		System.out.println( "Function 반환 함수 plus10 : " + 
			plus10.apply(1).apply(2)
		);
	}

}
