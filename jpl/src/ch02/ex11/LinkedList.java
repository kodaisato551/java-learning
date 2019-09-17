package ch02.ex11;

/**
 *Objectがたのフィールド
 *次の参照
 * @author Sato Kodai
 *
 */
public class LinkedList {

	private Object element;
	private LinkedList next;

	public static void main(String[] args) {
		LinkedList l2 = new LinkedList("v2", null);
		LinkedList l1 = new LinkedList("v1", l2);
		System.out.println(l1.toString());
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

}
