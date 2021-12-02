package advent.of.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

	public static String[] splitInput(String in, String token) {
		return in.split(token);
	}

	public static Map.Entry[] splitInputToMap(String in, String token, String token1) {
		List<Map.Entry> map = new ArrayList<>();
		Arrays.asList(in.split(token)).forEach(s -> {
			String[] pair = s.split(token1);
			if(pair.length > 0) map.add(Map.entry(pair[0], pair[1]));
		});
		return map.toArray(new Map.Entry[map.size()]);
	}

	/**
	 * Reads given resource file as a string.
	 *
	 * @param fileName path to the resource file
	 * @return the file's contents
	 * @throws IOException if read fails for any reason
	 */
	static String getResourceFileAsString(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			try (InputStream is = classLoader.getResourceAsStream(fileName)) {
				if (is == null) return null;
				try (InputStreamReader isr = new InputStreamReader(is);
					BufferedReader reader = new BufferedReader(isr)) {
					return reader.lines().collect(Collectors.joining(System.lineSeparator()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
