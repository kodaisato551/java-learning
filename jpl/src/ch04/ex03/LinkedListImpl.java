package ch04.ex03;

public class LinkedListImpl implements LinkedList {

	private Object element;
	private LinkedList next;

	public LinkedListImpl(Object element, LinkedList next) {
		this.element = element;
		this.next = next;
	}

	public LinkedListImpl(Object element) {
		this(element, null);
	}

	@Override
	public void add(Object element) {
		LinkedList it = this;
		while (it.getNext() != null) {
			it = it.getNext();
		}
		it.setNext(new LinkedListImpl(element));
	}

	@Override
	public Object getElement() {
		return element;
	}

	@Override
	public int size() {
		int count = 0;
		LinkedList it = this;
		while (it != null) {
			it = it.getNext();
			count++;
		}
		return count;
	}

	@Override
	public void setNext(LinkedList next) {
		this.next = next;
	}

	@Override
	public LinkedList getNext() {
		return next;
	}

}
