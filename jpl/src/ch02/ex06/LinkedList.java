package ch02.ex06;

import ch02.ex05.Vehicle;

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
		Vehicle v1 = new Vehicle(10, 20, "a");
		Vehicle v2 = new Vehicle(30, 40, "b");
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
