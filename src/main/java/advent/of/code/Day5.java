package advent.of.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day5 {

	private static HashMap<Integer, HashSet<Integer>> lines = new HashMap<>(), overlaps = new HashMap<>();

	public static void main(String[] args) {
		String input = getResourceFileAsString("day5.txt");
		if(input != null && !input.equals("")) {
			for (String line : input.split("\r\n")) processLines(line);
			part1();
			part2();
		}
	}

	private static void part1() {
		int total = 0;
		for (HashSet<Integer> row : overlaps.values()) {
			total += row.size();
		}
		System.out.println("Total amount of overlaps: " + total);
	}

	private static void part2() {

	}

	private static void processLines(String line) {
		String[] values = line.split(" -> |,");
		if (values.length == 4) {
			int[] nums = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
			int x1 = nums[0], x2 = nums[2], y1 = nums[1], y2 = nums[3];
			if (x1 == x2) {
				addRowIfNeeded(lines, x1);
				HashSet<Integer> row = lines.get(x1);
				int start = y1 < y2 ? y1 : y2;
				int end = y1 > y2 ? y1 : y2;
				for (int i = start; i <= end ; i++) {
					if (!row.add(i)) {
						addRowIfNeeded(overlaps, x1);
						overlaps.get(x1).add(i);
					}
				}
			} else if (y1 == y2) {
				int start = x1 < x2 ? x1 : x2;
				int end = x1 > x2 ? x1 : x2;
				for (int i = start; i <= end ; i++) {
					addRowIfNeeded(lines, i);
					if(!lines.get(i).add(y1)) {
						addRowIfNeeded(overlaps, i);
						overlaps.get(i).add(y1);
					}
				}
			} else {
				int startX, startY, yDir;
				int length = Math.abs(x1 - x2);
				if (x1 < x2) {
					startX = x1;
					startY = y1;
					yDir = (y2 - y1) / length;
				} else {
					startX = x2;
					startY = y2;
					yDir = (y1 - y2) / length;
				}
				for (int i = 0; i <= length; i++) {
					int currentX = startX + i, currentY = startY + i * yDir;
					addRowIfNeeded(lines, currentX);
					if(!lines.get(currentX).add(currentY)) {
						addRowIfNeeded(overlaps, currentX);
						overlaps.get(currentX).add(currentY);
					}
				}
			}
		}
	}

	private static void addRowIfNeeded(HashMap<Integer, HashSet<Integer>> map, int key) {
		if (map.get(key) == null) {
			map.put(key, new HashSet<>());
		}
	}

}
