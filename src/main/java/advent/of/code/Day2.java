package advent.of.code;

import java.util.Map;

import static advent.of.code.Utils.*;

public class Day2 {

	private static Map.Entry[] map;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day2.txt");
		if(input != null && !input.equals("")) {
			map = splitInputToMap(input, "\r\n", " ");
			part1();
			part2();
		}
	}

	public static void part1() {
		int x = 0, y = 0;
		for (Map.Entry<String, String> e : map) {
			int val = Integer.parseInt(e.getValue());
			switch (e.getKey()) {
				case "forward":
					x += val;
					break;
				case "up":
					y -= val;
					break;
				case "down":
					y += val;
					break;
				default:
					System.out.println("invalid direction found: " + e.getKey());
			}
		}
		System.out.println(x + ", " + y);
		System.out.println(x * y);
	}

	public static void part2() {
		int x = 0, y = 0, aim = 0;
		for (Map.Entry<String, String> e : map) {
			int val = Integer.parseInt(e.getValue());
			switch (e.getKey()) {
				case "forward":
					x += val;
					y += val * aim;
					break;
				case "up":
					aim -= val;
					break;
				case "down":
					aim += val;
					break;
				default:
					System.out.println("invalid direction found: " + e.getKey());
			}
		}
		System.out.println(x + ", " + y);
		System.out.println(x * y);
	}

}
