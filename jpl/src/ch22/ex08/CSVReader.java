package ch22.ex08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
    public static List<String[]> readCSVReader(Readable source, int cellSize) throws IOException {

        Scanner scanner = new Scanner(source);
        List<String[]> vals = new ArrayList<>();
        String exp = "^([^,]*),([^,]*),([^,]*),([^,]*)$";
        Pattern pattern = Pattern.compile(exp.toString(), Pattern.MULTILINE);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line != null) {
                String[] cells = new String[cellSize];
                MatchResult match = scanner.match();
                for (int i = 0; i < cellSize; i++)
                    cells[i] = match.group(i + 1);
                vals.add(cells);
                scanner.nextLine();// 改行を読み飛ばし
            } else {
                throw new IOException();
            }
        }
        IOException ioException = scanner.ioException();
        if (ioException != null) {
            throw ioException;
        }
        return vals;

    }
}
