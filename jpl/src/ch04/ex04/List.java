package ch04.ex04;

public interface List<E> extends Collection<E> {

	/**
	 * 指定したindexの要素を取得する
	 * @param index
	 * @return
	 */
	E getElementAt(int index);

	/**
	 * 指定したインデックスに要素を追加する
	 * @param index
	 * @param element
	 */
	void addElementAt(int index, E element);

}
