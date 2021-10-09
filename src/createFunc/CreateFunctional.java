package createFunc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateFunctional {

	public static void main(String[] args) {
		System.out.println(tripleFunction("3 + 4 = ", 3d, 4d, (n1, n2, n3) -> n1 + (n2 + n3))); 
		System.out.println(tripleFunction(2d, 3d, 4d, (n1, n2, n3) -> n1 + n2 + n3)); 
		System.out.println(tripleFunction(2d, 3d, 4d, (n1, n2, n3) -> n1 * n2 * n3)); 
		System.out.println(tripleFunction("경기도", "고양시", "일산동구", (s1, s2, s3) -> s1 + " " + s2 + " " + s3)); 
		
		GenericInterface<Integer> iFunc = g1 -> String.valueOf(g1);
		System.out.println(iFunc.mkString(12));
		GenericInterface<Double> dFunc = g1 -> String.valueOf(g1);
		System.out.println(dFunc.mkString(12.344));
		GenericInterface<BigDecimal> bdFunc = g1 -> String.valueOf(g1);
		System.out.println(bdFunc.mkString(new BigDecimal("110.25")));
		
		System.out.println("==================================【 Identity 】==============================================");
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		System.out.println(map(list, n -> n*2));
		System.out.println(map(list, n -> new BigDecimal(n).multiply(new BigDecimal("3.11"))));
		System.out.println(map(list, Function.identity()));
		
	}

	private static <T1, T2, T3> Object tripleFunction(T1 t1, T2 t2, T3 t3, TripleFunction<T1, T2, T3, Object> function) {
		return function.apply(t1, t2, t3);
	}
	
	private static <T,R> List<R> map(List<T> list, Function<T, R> function) {
		return list.stream().map(t -> function.apply(t)).collect(Collectors.toList());
	}
	
}

@FunctionalInterface
interface TripleFunction<T1, T2, T3, R> {
	R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface GenericInterface<T> {
	String mkString(T t); 
}
