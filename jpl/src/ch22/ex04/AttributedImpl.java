package ch22.ex04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImpl extends Observable implements Atrributed {
    private Map<String, Attr> attrMap = new HashMap<>();

    @Override
    public void add(Attr newAttr) {
        attrMap.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers(new ChangeInfo(Action.ADDED, newAttr));
    }

    @Override
    public Attr find(String attrName) {
        return null;
    }

    @Override
    public Attr remove(String attrName) {
        return null;
    }

    @Override
    public Iterator<Attr> attrs() {
        return null;
    }


    static class ChangeInfo {
        final Action action;
        final Attr attr;

        ChangeInfo(Action action, Attr attr) {
            this.action = action;
            this.attr = attr;
        }
    }

    static enum Action {
        ADDED, REMOVED;
    }
}
