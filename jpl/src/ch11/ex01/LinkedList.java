package ch11.ex01;

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

}
