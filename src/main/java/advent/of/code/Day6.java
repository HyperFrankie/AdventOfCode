package advent.of.code;

import java.util.Arrays;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day6 {

	private static int[] ages;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day6.txt");
		if(input != null && !input.equals("")) {
			ages = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
			part1();
			part2();
		}
	}

	private static void part1() {
		long[] options = new long[7];
		for (int i = 0; i <= 6; i++) options[i] = recursiveCalculation(262 - i);
		System.out.println(Arrays.toString(options));
		long total = 0;
		for (int age : ages) {
			total += options[age];
		}
		System.out.println(total);
	}

	private static long recursiveCalculation(int daysLeft) {
		long kids = (int) Math.floor(daysLeft / 7);
		long result = 1;
		for (int i = 1; i <= kids; i++) result += recursiveCalculation(daysLeft - 2 - 7 * i);
		return result;
	}

	private static void part2() {

	}

}