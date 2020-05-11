package ch22.ex04;

public interface Atrributed {
    void add(Attr newAttr);

    Attr find(String attrName);

    Attr remove(String attrName);

    java.util.Iterator<Attr> attrs();


}
