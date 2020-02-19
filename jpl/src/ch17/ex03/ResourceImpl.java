package ch17.ex03;

public class ResourceImpl implements Resource {
    Object key;
    boolean needsRelease;

    ResourceImpl(Object key) {
        this.key = key;
        //外部リソースの設定
        needsRelease = true;
    }


    @Override
    public void use(Object key, Object... args) {
        if (key != this.key) {
            throw new IllegalArgumentException("wrong key");
        }
        //リソースの使用
    }

    @Override
    public synchronized void release() {
        if (needsRelease) {
            needsRelease = false;
            //リソースの解放
        }
    }
}
