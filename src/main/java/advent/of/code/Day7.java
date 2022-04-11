package advent.of.code;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day7 {

	private static LinkedHashMap<Integer, Integer> counts = new LinkedHashMap<>();
	private static int max = 0, min = 1000;
	private static Integer[] numbers;
	private static int amount;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day7.txt");
		if(input != null && !input.equals("")) {
			int[] in = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).sorted().toArray();
			for (int i : in) {
				if (counts.containsKey(i)) {
					counts.replace(i, counts.get(i) + 1);
				} else {
					counts.put(i, 1);
					if (i < min) min = i;
					if (i > max) max = i;
				}
			}
			numbers = counts.keySet().toArray(Integer[]::new);
			amount = numbers.length;
			for(int i : numbers) {
				System.out.println("number " + i + "\t occurs " + counts.get(i) + "\t times in the input");
			}
			part1();
			part2();
		}
	}

	private static void part1() {
		int countL = 0, countR = 0, nextL = min, nextR = max;
		long changeL = 0, changeR = 0;
		int[] costs = new int[amount];
		for (int i = 1; i < amount; i++) {
			int numL = numbers[i];
			int count = counts.get(nextL);
			countL += count;
			changeL += countL * (numL - nextL);
			costs[i] += changeL;
			nextL = numL;
			int numR = numbers[amount - 1 - i];
			count = counts.get(nextR);
			countR += count;
			changeR += countR * (nextR - numR);
			costs[amount - 1 - i] += changeR;
			nextR = numR;
		}
		int min = Arrays.stream(costs).min().orElse(-1);
		System.out.println("min: " + min);
	}

	private static void part2() {
		int[] costs = new int[max - min + 1];
		for (int i = 0; i <= max - min; i++) {
			for (int j = 0; j < amount; j++) {
				int num = numbers[j];
				int diff = Math.abs(num - i);
				costs[i] += (diff * (diff + 1)) / 2 * counts.get(num);
			}
		}
		int min = Arrays.stream(costs).min().orElse(-1);
		System.out.println("min: " + min);
	}

}
