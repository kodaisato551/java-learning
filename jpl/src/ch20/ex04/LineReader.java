package ch20.ex04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
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
                strings.add(sb.toString());
            }
        }
        return strings;
    }

}
