package interpret.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成されたオブジェクトの保存
 */
public class ObjectPool {
    private static final ObjectPool instance = new ObjectPool();
    private final List<Object> list = new ArrayList<>();

    private ObjectPool() {
    }

    public void add(Object e) {
        list.add(e);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public void clear() {
        list.clear();
    }

    public List<Object> getAll() {
        return list;
    }


    public static ObjectPool getInstance() {
        return ObjectPool.instance;
    }

}
