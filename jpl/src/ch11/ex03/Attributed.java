package ch11.ex03;

/**
 * interfaceにも同様の型パラメータをこしらえる
 * TODO w:Attributedオブジェクトに対してはどのような意味を持つか？　－＞　わかりません、、、
 * TODO M:testcode
 * @author Sato Kodai
 *
 * @param <E>
 */
public interface Attributed<E> {
	void add(Attr<E> newAttr);

	void find(String attrName);

	Attr<E> remove(String attrNamer);

	java.util.Iterator<Attr<E>> attrs();

}
