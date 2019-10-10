package ch04.ex03;

/**
 * リンクドリストが持つべき契約
 * ・要素を追加する
 * ・要素を取得する
 * ・要素サイズを取得する
 * ・次の要素を指すポインタをセットする
 * ・次の要素を指すポインタをゲットする
 * @author Sato Kodai
 *
 */
public interface LinkedList {

	/**
	 *要素を追加します(末尾）
	 */
	void add(Object element);

	/**
	 *要素を取得します
	 * @return Object
	 */
	Object getElement();

	/**
	 * 要素サイズを取得します
	 * @return int
	 */
	int size();

	/**
	 * 次の要素をセットします
	 * @param next
	 */
	void setNext(LinkedList next);

	/**
	 * 次の要素を取得します
	 */
	LinkedList getNext();

}
