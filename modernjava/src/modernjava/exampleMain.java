package modernjava;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import modernjava.cal.CacultatorSevice;
import modernjava.cal.impl.AddCaculation;
import modernjava.cal.impl.CaculationFunction;
import modernjava.cal.impl.DivideCaculation;
import modernjava.cal.impl.MultiCaculation;
import modernjava.cal.impl.SubstractCaculation;

public class exampleMain {

	public static void main(String[] args) {
		
		List<Integer> arryList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		String result = (String) arryList.stream().map(String::valueOf).collect(joining(" : "));
		System.out.println(result);
		
		CacultatorSevice addcalService = new AddCaculation() ;
		int addCal = addcalService.caculate(1, 1);
		System.out.println("덧셈 : " + addCal);
		
		CacultatorSevice substractcalService = new SubstractCaculation() ;
		int substractCal = substractcalService.caculate(1, 1);
		System.out.println("뺄셈 : " + substractCal);
		
		CacultatorSevice multicalService = new MultiCaculation() ;
		int multiCal = multicalService.caculate(1, 1);
		System.out.println("곱셈 : " + multiCal);
		
		CacultatorSevice dividecalService = new DivideCaculation() ;
		int divideCal = dividecalService.caculate(10, 5);
		System.out.println("나눗셈 : " + divideCal);
		
		CaculationFunction fcal = new CaculationFunction();
		int addVal = fcal.fCaculate(1, 2, (n1, n2) -> n1 + n2);
		int subsVal = fcal.fCaculate(6, 2, (n1, n2) -> n1 - n2);
		System.out.println("Functional Caculate addVal : " + addVal);
		System.out.println("Functional Caculate subsVal: " + subsVal);
		
		BiFunction<Integer, Integer, Integer> biFunction = (n1, n2) -> n1 * n2;
		System.out.println("Bi Function : " + biFunction.apply(2, 5));
		
	}

}
