package advent.of.code;

import java.util.*;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day4 {

	private static HashMap<Integer, Integer> orderMap = new HashMap<>();
	private static Card winning;
	private static int minDraws = 100;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day4.txt");
		if(input != null && !input.equals("")) {
			String[] lines = input.split("\r\n");
			int[] order = Arrays.stream(lines[0].split(",")).mapToInt(Integer::parseInt).toArray();
			for (int i = 0; i < order.length; i++) orderMap.put(order[i], i);
			Card[] cards = new Card[(lines.length - 1) / 6];
			for (int i = 0; i < (lines.length - 1) / 6; i++) {
				int[][] cardNums = new int[5][5];
				for (int j = 0; j < 5; j++) {
					cardNums[j] = Arrays.stream(lines[i * 6 + j + 2].trim().split(" +")).mapToInt(Integer::parseInt).toArray();
				}
				Card card = new Card(cardNums);
				cards[i] = card;
				card.print();
				card.calculate();
			}
			System.out.println("final minimum: " + minDraws);
		}
	}

	private static class Card {

		private int[][] numbers;
		int min = 100;
		private Set<Integer> numSet = new HashSet<>();

		public Card(int[][] nums) {
			numbers = nums;
			for (int[] il : numbers) for (int i : il) numSet.add(i);
		}

		public void print() {
			System.out.println(numSet);
			Arrays.asList(numbers).forEach(a -> System.out.println(Arrays.toString(a)));
		}

		public void calculate() {
			for (int i = 0; i < numbers.length; i++) {
				int horizontalMax = Arrays.stream(numbers[i]).map(j -> orderMap.get(j)).max().getAsInt();
				if (horizontalMax < min) min = horizontalMax;
				int verticalMax = 100;
				for (int j = 0; j < numbers.length; j++) {
					int index = orderMap.get(numbers[j][i]);
					if (index > verticalMax) verticalMax = index;
				}
				if (verticalMax < min) min = verticalMax;
			}
			System.out.println("Current card min: " + min);
			if (min < minDraws) {
				winning = this;
				minDraws = min;
				System.out.println("new record");
			}
		}
	}

}
