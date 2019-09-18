package ch02.ex06;

import ch02.ex05.Vehicle;

/**
 * LinkedListにVehicleをつなげてmainメソッドで表示する
 *
 */
public class LinkedList {

	private Object element;
	private LinkedList next;

	public static void main(String[] args) {
		Vehicle v1 = new Vehicle("a");
		v1.setDirection(10);
		v1.setSpeed(20);
		Vehicle v2 = new Vehicle("b");
		v2.setDirection(30);
		v2.setSpeed(40);
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
