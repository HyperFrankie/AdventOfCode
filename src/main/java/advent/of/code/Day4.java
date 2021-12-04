package advent.of.code;

import java.util.Arrays;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day4 {

	private static int[] order;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day3.txt");
		if(input != null && !input.equals("")) {
			String[] lines = input.split("\r\n");
			order = Arrays.stream(lines[0].split(",")).mapToInt(Integer::parseInt).toArray();
			for (int i = 1; i < lines.length - 6; i += 6) {
				Arrays.spliterator(Arrays.copyOfRange(lines, i, i + 6))
				for (int j = 1; j < 6; j++) {
					Arrays.stream(lines[i + j].split(" *")).mapToInt(Integer::parseInt).toArray();
				}
			}
		}
	}

}
