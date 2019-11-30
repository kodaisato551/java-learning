package ch13.ex04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringCount {
	private static final String ERROR_MSG = "invalid line text type ";

	public static List<Object> fileReader(String pathname) throws FileNotFoundException, IOException {
		List<Object> result = new ArrayList<>();
		File file = new File(pathname);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String text;
			int count = 0;

			while ((text = br.readLine()) != null) {
				String[] keyAndValue = text.split(" ");
				count++;

				if (keyAndValue.length != 2) {
					result.add(ERROR_MSG + count);
					continue;
				}

				switch (keyAndValue[0]) {
				case "Boolean":
					result.add(Boolean.parseBoolean(keyAndValue[1]));
					break;
				case "Integer":
					result.add(Integer.parseInt(keyAndValue[1]));
					break;
				case "Double":
					result.add(Double.parseDouble(keyAndValue[1]));
					break;
				case "Character":
					result.add((keyAndValue[1].toCharArray()[0]));
					break;
				case "Float":
					result.add(Float.parseFloat(keyAndValue[1]));
					break;
				case "Long":
					result.add(Long.parseLong(keyAndValue[1]));
					break;
				case "Byte":
					result.add(Byte.parseByte(keyAndValue[1]));
					break;
				case "Short":
					result.add(Short.parseShort(keyAndValue[1]));
					break;
				default:
					result.add(ERROR_MSG + count);
					break;
				}
			}
		}

		return result;
	}
}
