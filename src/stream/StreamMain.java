package stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

	public static void main(String[] args) {

		System.out.println(
			Stream.of(1,2,3,4,5,6,7,8,9,10)
				.filter(n -> n > 3 && n < 9)
				.map(n -> n * 2)
				.filter(n -> n > 20)
				.findFirst()
				.orElse(0)
		);
		
		System.out.println(
			Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8).collect(Collectors.toList())
		);
		System.out.println(
			Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8).collect(Collectors.toSet())
		);
		System.out.println(
			Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8)
				.map(String::valueOf)
				.collect(Collectors.joining(", "))
		);
		System.out.println(
			Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8)
			.map(String::valueOf)
			.collect(Collectors.joining(", ", "{", "}"))
		);
		
		final Integer int127 = 127;
		final Integer int128 = 128;
		System.out.println(
				Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8,127)
				.filter(i -> i == int127)
				.findFirst()
				.orElse(0)
				);
		System.out.println(
				Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8,128)
				.filter(i -> i == int128)
				.findFirst()
				.orElse(0)
				);
		System.out.println(
				Stream.of(1,2,3,4,5,6,7,8,9,10,4,5,6,7,8,128)
				.filter(i -> i.equals(int128))
				.findFirst()
				.orElse(0)
				);
	}

}
