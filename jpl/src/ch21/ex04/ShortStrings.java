package ch21.ex04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * TODO フィルタとは何か？
 */
public class ShortStrings implements ListIterator<String> {

    private Iterator<String> strings;
    private String nextShort;
    private final int maxLen;


    public ShortStrings(Iterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
    }


    @Override
    public boolean hasNext() {
        if (nextShort != null) {
            return true;
        }
        while (strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen) {
                return true;
            }
        }
        nextShort = null;
        return false;
    }

    @Override
    public String next() {

        if (nextShort == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        String n = nextShort;
        nextShort = null;
        return n;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public String previous() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String s) {

    }

    @Override
    public void add(String s) {

    }
}
