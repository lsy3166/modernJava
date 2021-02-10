package modernjava.cal;

@FunctionalInterface
public interface CaculationFunctionInterface<T1 , T2 , R> {
	R apply(T1 t1, T2 t2);
}
