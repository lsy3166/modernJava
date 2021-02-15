package modernjava;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ParallelMain {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Arrays.asList(1,2,3,4,5,6,7,8)
			  .stream()
			  .map(number -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return number;
			  })
			  .forEach(number -> System.out.println(number));
		System.out.println("Stream : " + String.valueOf(System.currentTimeMillis() - start) + "ms");
		
		//-----------------------------------------------------------------------------------
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7"); // 코어 개수 셋팅 : 7 => 8코어, 3 => 4코어
		//-----------------------------------------------------------------------------------
		long start2 = System.currentTimeMillis();
		Arrays.asList(1,2,3,4,5,6,7,8)
		.parallelStream()
		.map(number -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return number;
		})
		.forEach(number -> System.out.println(number));
		System.out.println("Parallel Stream : " + String.valueOf(System.currentTimeMillis() - start2) + "ms");
	}

}
