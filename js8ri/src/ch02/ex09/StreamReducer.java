package ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 */
public class StreamReducer {
    /**
     * ArrayListのストリームをArrayListに変換する
     *
     * @param stream
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> reduceArrayListStream(Stream<ArrayList<T>> stream) {
        ArrayList<T> list = new ArrayList<>();
        return stream.reduce(list, (elm1, elm2) -> {
                    elm1.addAll(elm2);
                    return elm1;
                }
                , (elm1, elm2) -> {
                    elm1.addAll(elm2);
                    return elm1;
                }
        );
    }
}
