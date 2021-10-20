package methodRef;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReferenceMain {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		list.stream()
			//.forEach(n -> System.out.println(n));
			.forEach(System.out::println);
		
		List<BigDecimal> bdList = Arrays.asList(new BigDecimal("10.23"), new BigDecimal("25.02"), new BigDecimal("5.50"));
		System.out.println(
			bdList.stream()
				  //.sorted((bd1, bd2) -> bd1.compareTo(bd2))
				  //.sorted(BigDecimalUtil::compare)
				  .sorted(BigDecimal::compareTo)
				  .collect(Collectors.toList())
		);
		
		List<String> strList = Arrays.asList("1", "2", "3");
		String targetStr = "2";
		System.out.println(
			strList.stream()
				   //.anyMatch(x -> x.equals("2"))
				   //.anyMatch("3"::equals)
				   .anyMatch(targetStr::equals)
		);
		
		// firstClassFunction 재사용
		methodReference01();
		methodReference02();
		
		// 3가지 functional 표현
		System.out.println("================== 3가지 functional 표현 ====================");
		List<Function<Integer, String>> funcList = Arrays.asList(
			i -> String.valueOf(i * 2)
			, MethodReferenceMain::dobleThenToString
			, MethodReferenceMain::hashToString
		);
		
		for (Function<Integer, String> func : funcList) {
			System.out.println(func.apply(7));
		}
	}
	
	private static void methodReference01() {
		System.out.println(firstClassFunction("Lambda Express", 3, i -> String.valueOf(i*2)));
		System.out.println(firstClassFunction("Method Reference", 3, MethodReferenceMain::dobleThenToString));
	}
	
	private static String firstClassFunction(String name, int i, Function<Integer, String> func) {
		return "[" + name +"] Result is " + String.valueOf(func.apply(i) + ".");
	}
	
	private static String dobleThenToString(int i) {
		return String.valueOf(i*2);
	}
	
	private static String hashToString(int i) {
		return String.valueOf("#" + i);
	}
	
	private static void methodReference02() {
		System.out.println(getDoubleLambdaExpess().apply(3));
		System.out.println(getDoubleMethodReference().apply(3));
	}

	private static Function<Integer, String> getDoubleLambdaExpess() {
		return n -> String.valueOf(n*2) ;
	}
	
	private static Function<Integer, String> getDoubleMethodReference() {
		return MethodReferenceMain::dobleThenToString ;
	}

}

class BigDecimalUtil {
	public static int compare(BigDecimal bd1, BigDecimal bd2) {
		return bd2.compareTo(bd1);
	}
}
