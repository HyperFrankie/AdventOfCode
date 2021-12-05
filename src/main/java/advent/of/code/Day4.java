package advent.of.code;

import java.util.*;

import static advent.of.code.Utils.getResourceFileAsString;

public class Day4 {

	private static int[] order;
	private static HashMap<Integer, Integer> orderMap = new HashMap<>();
	private static Card[] cards;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day4.txt");
		if(input != null && !input.equals("")) {
			processInput(input.split("\r\n"));
			part1();
			part2();
		}
	}

	private static void part1() {
		Card winning = null;
		int minDraws = 100;
		for (Card card : cards) {
			if(card.min < minDraws) {
				minDraws = card.min;
				winning = card;
			}
		}
		result(minDraws, winning);
	}

	private static void part2() {
		Card winning = null;
		int maxDraws = 0;
		for (Card card : cards) {
			if (card.min > maxDraws) {
				maxDraws = card.min;
				winning = card;
			}
		}
		result(maxDraws, winning);
	}

	private static void result(int draws, Card winning) {
		System.out.println("Winner: ");
		winning.print();
		System.out.println("draw amount: " + draws);
		int lastNum = order[draws];
		int restSum = winning.getRestNumsSum();
		System.out.println("rest numbers sum: " + restSum);
		System.out.println("final answer: " + lastNum * restSum);
	}

	private static void processInput(String[] lines) {
		order = Arrays.stream(lines[0].split(",")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < order.length; i++) orderMap.put(order[i], i);
		cards = new Card[(lines.length - 1) / 6];
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
	}

	private static class Card {

		private int[][] numbers;
		int min = 100;

		public Card(int[][] nums) {
			numbers = nums;
		}

		public void print() {
			Arrays.asList(numbers).forEach(a -> System.out.println(Arrays.toString(a)));
		}

		public void calculate() {
			for (int i = 0; i < numbers.length; i++) {
				int horizontalMax = Arrays.stream(numbers[i]).map(j -> orderMap.get(j)).max().getAsInt();
				if (horizontalMax < min) min = horizontalMax;
				int verticalMax = 0;
				for (int j = 0; j < numbers.length; j++) {
					int index = orderMap.get(numbers[j][i]);
					if (index > verticalMax) verticalMax = index;
				}
				if (verticalMax < min) min = verticalMax;
			}
			System.out.println("Current card min: " + min);
		}

		public int getRestNumsSum() {
			HashSet<Integer> drawnNums = new HashSet<>();
			List<Integer> restNums = new ArrayList<>();
			Arrays.stream(Arrays.copyOfRange(order, 0, min + 1)).forEach(drawnNums::add);
			for (int i = 0; i < numbers.length; i++) {
				for (int j = 0; j < numbers.length; j++) {
					int currentNum = numbers[i][j];
					if (!drawnNums.contains(currentNum)) restNums.add(currentNum);
				}
			}
			System.out.println(Arrays.toString(restNums.toArray()));
			return restNums.stream().mapToInt(Integer::intValue).sum();
		}
	}

}
