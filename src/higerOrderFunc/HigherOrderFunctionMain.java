package higerOrderFunc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HigherOrderFunctionMain {

	public static void main(String[] args) {
		
		Function<Function<Integer, String>, String> hofunc = i -> i.apply(100);
		System.out.println(hofunc.apply(i -> "#" + i));
		
		Function<Integer, Function<Integer, Integer>> hof2 = n -> n2 -> n + n2;
		System.out.println(hof2.apply(5).apply(6));
		
		Function<Integer, Function<Integer, Function<Integer, Integer>>> hof3 = n -> (n1 -> (n2 -> n + n1 * n2));
		System.out.println(hof3.apply(4).apply(2).apply(3));
		
		Function<Integer, String> f = i -> "#" + i;
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		System.out.println(list.stream().map(n -> f.apply(n)).collect(Collectors.toList()));
	}

}
