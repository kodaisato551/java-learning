package ch02.ex02;


public class LinkedList {

	private Object element;
	private LinkedList next;

	public LinkedList(Object element) {
		this(element, null);
	}

	public LinkedList(Object element, LinkedList next) {
		this.element = element;
		this.next = next;
	}

	public Object getElement() {
		return element;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

}
