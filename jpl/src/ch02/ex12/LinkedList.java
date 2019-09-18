package ch02.ex12;

/**
 *可変長引数の例として,addAllメソッドを追加
 */
public class LinkedList {

	private Object element;
	private LinkedList next;

	public static void main(String[] args) {
		LinkedList l2 = new LinkedList("v2", null);
		LinkedList l1 = new LinkedList("v1", l2);
		l1.addAll("v3", "v4");

		System.out.println(l1);
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		LinkedList itList = this;
		sBuilder.append("[");

		while (itList != null) {
			sBuilder.append(itList.getElement());
			if (itList.getNext() != null) {
				sBuilder.append(",");
			}
			itList = itList.getNext();
		}

		sBuilder.append("]");
		return sBuilder.toString();

	}

	public LinkedList(Object element) {
		this.element = element;
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

	/**
	 *まとめてLinkedList対してADDする際に
	 *可変長引数を使う
	 * @param objects
	 */
	public void addAll(Object... objects) {
		LinkedList it = this;
		while (it.getNext() != null) {
			it = it.getNext();
		}
		for (int i = 0; i < objects.length; i++) {
			it.setNext(new LinkedList(objects[i]));
			it = it.getNext();
		}

	}

}
