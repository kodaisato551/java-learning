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

    /**
     * 特定の型情報をキーとして渡し、その型のObjectのリストを返す
     * <p>
     * オブジェクトがない場合は
     *
     * @param clazz ほしい型情報
     * @return
     */
    public List<Object> grep(Class<?> clazz) {
        List<Object> greppedList = new ArrayList<>();
        for (Object obj : list) {
            if (obj.getClass() == clazz) {
                greppedList.add(obj);
            }
        }
        return greppedList;
    }

    /**
     * index の要素を上書き
     *
     * @param index
     */
    public void setObject(int index, Object object) {
        list.set(index, object);
    }

    /**
     * 表示用のStringを得る
     */
    public String getDisplayName(Object obj) {
        return obj.getClass().getSimpleName() + "#" + obj.hashCode();
    }

    public static ObjectPool getInstance() {
        return ObjectPool.instance;
    }

}
