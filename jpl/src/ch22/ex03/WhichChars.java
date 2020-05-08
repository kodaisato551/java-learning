package ch22.ex03;

import java.util.HashSet;
import java.util.Set;

public class WhichChars {
    private Set<Character> used = new HashSet<>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            used.add(str.charAt(i));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Character c : used) {
            sb.append(c);
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        WhichChars w = new WhichChars("Testing 1 2 3");
        System.out.println(w.toString());
    }
}
