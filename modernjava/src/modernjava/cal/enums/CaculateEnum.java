package modernjava.cal.enums;

import java.util.function.BiFunction;

public enum CaculateEnum {
	PLUS((num1, num2) -> num1 + num2),
	MINUS((num1, num2) -> num1 - num2),
	MUTIPLY((num1, num2) -> num1 * num2),
	DIVIDE((num1, num2) -> num1 / num2);
	
	BiFunction<Integer, Integer, Integer> expression;
	CaculateEnum(BiFunction<Integer, Integer, Integer> expression) {
		this.expression = expression;	
	}

	public Integer calcuate(Integer num1, Integer num2) {
		return this.expression.apply(num1, num2);
	}
}
