package modernjava.cal.impl;

import modernjava.cal.CaculationFunctionInterface;

public class CaculationFunction {

	public <T1, T2> int fCaculate(T1 number1, T2 number2, CaculationFunctionInterface<T1, T2, Integer> function) {
		return function.apply(number1, number2);
	}
	
}
