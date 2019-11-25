package ch12.ex01;

public class LinkedList<E> {

	private E element;
	private LinkedList<E> next;

	public LinkedList(E element) {
		this(element, null);
	}

	public LinkedList(E element, LinkedList<E> next) {
		this.element = element;
		this.next = next;
	}

	public E getElement() {
		return element;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	/**
	 *List内の要素を探索し同等ものはLinkedListを返します。
	 *@throws ObjectNotFoundExeception
	 */
	public LinkedList<E> find(E keyElement) throws ObjectNotFoundExeception {
		LinkedList<E> it = this;
		while (it != null) {
			if (it.getElement().equals(keyElement)) {
				return it;
			}
			it = it.getNext();
		}
		throw new ObjectNotFoundExeception(keyElement);
	}

}
