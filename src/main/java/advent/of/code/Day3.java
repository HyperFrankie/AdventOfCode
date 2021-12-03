package advent.of.code;

import java.util.Arrays;
import java.util.Map;

import static advent.of.code.Utils.getResourceFileAsString;
import static advent.of.code.Utils.splitInputToMap;

public class Day3 {

	private static String[] lines;

	public static void main(String[] args) {
		String input = getResourceFileAsString("day2.txt");
		if(input != null && !input.equals("")) {
			lines = input.split("\r\n");
			part1();
//			part2();
		}
	}

	public static void part1() {

		int[] counts = {0,0,0,0,0,0,0,0,0,0,0,0};
		for (String l : lines) {
			char[] chars = l.toCharArray();
			for (int i = 0; i < chars.length; i++) counts[i] += chars[i] * 2 - 1;
		}
		byte[] result1 = {0,0,0,0,0,0,0,0,0,0,0,0};
		byte[] result2 = {0,0,0,0,0,0,0,0,0,0,0,0};
		for (int i = 0; i < counts.length; i++) {
			result1[i] = (byte) (counts[i] > 0 ? 1 : 0);
			result2[i] = (byte) (counts[i] > 0 ? 0 : 1);
		}
		int resultNum1 = 0, resultNum2 = 0;
		for (int i = 0, j = result1.length - 1; i < result1.length; i++, j--) {
			resultNum1 += result1[i] * Math.pow(2, j);
			resultNum2 += result2[i] * Math.pow(2, j);
		}
		System.out.println(arrayPrint1(counts) + ", " + arrayPrint(result1) + ", " + arrayPrint(result2));
		System.out.println(resultNum1 + ", " + resultNum2);
		System.out.println(resultNum1 * resultNum2);
		
	}

	public static String arrayPrint(byte[] bitArray) {
		String result = "";
		for (byte b: bitArray) {
			result += b;
		}
		return result;
	}

	public static String arrayPrint1(int[] bitArray) {
		String result = "";
		for (int b: bitArray) {
			result += b + ",";
		}
		return result;
	}

	public static void part2() {

	}

}
