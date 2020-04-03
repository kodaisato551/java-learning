package ch20.ex06;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 結果をvalueに格納する
 */
public class NameOpValue {


    private static void parse(Reader source) throws IOException {
        Map<String, Double> nameAndValue = new HashMap<>();//変数をストックする
        StreamTokenizer in = new StreamTokenizer(source);
        String name = null;
        double increment = 0;
        double value = 0;
        char op = 0;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            switch (in.ttype) {
                case StreamTokenizer.TT_WORD:
                    if (op != 0) {
                        if (nameAndValue.containsKey(name)) {
                            value = nameAndValue.get(name);//ひとつ前の変数値を取得
                        }
                        switch (op) {
                            case '+':
                                value += nameAndValue.get(in.sval);
                                nameAndValue.put(name + "+" + in.sval, value);
                                break;
                            case '-':
                                value -= nameAndValue.get(in.sval);
                                nameAndValue.put(name + "-" + in.sval, value);
                                break;
                            case '=':
                                nameAndValue.put(name, in.nval);
                                break;
                            default:
                                break;
                        }
                        name = null;
                        op = 0;
                    }
                    name = in.sval;
                    break;
                case '+':
                    op = '+';
                    break;
                case '-':
                    op = '-';
                    break;
                case '=':
                    op = '=';
                    break;
                case StreamTokenizer.TT_NUMBER:
                    if (name == null) {
                        throw new IllegalArgumentException();
                    }
                    value = 0;
                    if (nameAndValue.containsKey(name)) {
                        value = nameAndValue.get(name);//ひとつ前の変数値を取得
                    }
                    switch (op) {
                        case '+':
                            value += in.nval;
                            nameAndValue.put(name + "+" + in.nval, value);
                            break;
                        case '-':
                            value -= in.nval;
                            nameAndValue.put(name + "+" + in.nval, value);
                            break;
                        case '=':
                            nameAndValue.put(name, in.nval);
                            break;
                        default:
                            break;
                    }
                    name = null;
                    op = 0;
                    break;
                default:
                    break;
            }
        }
        Set<Map.Entry<String, Double>> results = nameAndValue.entrySet();
        for (Map.Entry<String, Double> result : results) {
            System.out.println(result.getKey() + " = " + result.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        parse(new FileReader("jpl\\src\\ch20\\ex06\\in.txt"));
    }


}
