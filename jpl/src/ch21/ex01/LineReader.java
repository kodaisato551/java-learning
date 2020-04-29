package ch21.ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LineReader extends FilterReader {
    private static final String CR = System.lineSeparator();

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected LineReader(Reader in) {
        super(in);
    }

    public List<String> readLine() throws IOException {
        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int r;
        while ((r = read()) != -1) {
            char ch = (char) r;
            sb.append(ch);
            if (sb.toString().endsWith(CR)) {
                strings.add(sb.toString().replace(CR, ""));
                sb = new StringBuilder();
            }
        }
        return strings;
    }

    /**
     * オリジナルなリストを受け取り、String.compareToに従いソート済みのコピーのリストを生成する
     *
     * @param orgList
     * @return
     */
    public List<String> createSortedList(List<String> orgList) {
        List<String> copiedList = new ArrayList<>(orgList);
        copiedList.sort(String::compareTo);
        return copiedList;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\study\\java\\java-learning\\jpl\\src\\ch21\\ex01\\in.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        LineReader lr = new LineReader(br);
        List<String> strs = lr.readLine();
        System.out.println("original strings : " + strs);
        List<String> sortedList = lr.createSortedList(strs);
        System.out.println("sorted strings : " + sortedList);
    }


}
