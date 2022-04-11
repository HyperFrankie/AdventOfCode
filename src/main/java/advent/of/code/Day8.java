package advent.of.code;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day8 {

	private static String[] in;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day8.txt");
		if(input != null && !input.equals("")) {
			in = input.split("\r\n");
			part1();
			part2();
		}
	}

	private static void part1() {
		String[] simple = Arrays.stream(in).map(s -> s.split(" \\| ")[1]).toArray(String[]::new);
		int count = 0;
		for (String s : in) {
			String[] sa = s.split(" ");
			if (sa.length != 4) System.out.println("error, length != 4\n" + Arrays.toString(sa));
			count += Arrays.stream(sa).mapToInt(String::length).filter(i -> i == 8 || (i > 2 && i < 5)).count();
		}
		System.out.println("count: " + count);
	}

	private static void part2() {

	}

}
