package advent.of.code;

import java.util.Arrays;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day7 {

	private static int[] positions;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day7.txt");
		if(input != null && !input.equals("")) {
			positions = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
			part1();
			part2();
		}
	}

	private static void part1() {

	}

	private static void part2() {

	}

}
