package ch02.ex07;

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
		Vehicle v1 = new Vehicle("a");
		Vehicle v2 = new Vehicle("b");
		LinkedList l2 = new LinkedList(v2, null);
		LinkedList l1 = new LinkedList(v1, l2);

		LinkedList itList = l1;
		while (itList != null) {
			Vehicle vehicle = (Vehicle) itList.getElement();
			System.out.println(vehicle.getID());
			itList = itList.getNext();
		}

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
