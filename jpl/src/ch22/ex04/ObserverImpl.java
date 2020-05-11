package ch22.ex04;


import java.util.Observable;
import java.util.Observer;

public class ObserverImpl implements Observer {

    AttributedImpl watching;

    public ObserverImpl(AttributedImpl attrImpl) {
        watching = attrImpl;
        watching.addObserver(this);
    }

    @Override
    public void update(Observable attrImpl, Object changeInfo) {
        if (attrImpl != watching) {
            throw new IllegalArgumentException();
        }
        AttributedImpl.ChangeInfo info = (AttributedImpl.ChangeInfo) changeInfo;
        System.out.println(info.action + ":" + info.attr);
    }

    public static void main(String[] args) {
        AttributedImpl a = new AttributedImpl();
        ObserverImpl o = new ObserverImpl(a);
        a.add(new Attr("a1", "a1value"));
        a.add(new Attr("a2", new Object()));
        a.remove("a1");
        a.add(new Attr("a3", 100));
        a.find("a3");
        a.remove("b");
    }

}
