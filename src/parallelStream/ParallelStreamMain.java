package parallelStream;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ParallelStreamMain {

	public static void main(String[] args) {
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3"); // "3" : core 4개를 사용.
		System.out.println("getCommonPoolParallelism Core Count : " + (java.util.concurrent.ForkJoinPool.getCommonPoolParallelism() + 1));
		
		long start = System.currentTimeMillis();
		Arrays.asList(1,2,3,4,5,6)
			.forEach(n -> consumerTimeUnit(n));
		System.out.println("[No Parallel] It took " + String.valueOf((System.currentTimeMillis() - start)/1000) + "seconds.");
		
		long start2 = System.currentTimeMillis();
		Arrays.asList(1,2,3,4,5,6)
			.parallelStream()
			.forEach(n -> consumerTimeUnit(n));
		System.out.println("[Parallel]  It took " + String.valueOf((System.currentTimeMillis() - start2)/1000) + "seconds.");
	}

	private static void consumerTimeUnit(int n) {
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
