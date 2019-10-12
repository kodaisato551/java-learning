package ch04.ex03;

public class LinkedListOrg implements Cloneable {

	private Object element;
	private LinkedListOrg next;

	public static void main(String[] args) {
		LinkedListOrg l2 = new LinkedListOrg("v2", null);
		LinkedListOrg l1 = new LinkedListOrg("v1", l2);
		l1.addAll("v3", "v4");

		System.out.println(l1.size());
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		LinkedListOrg itList = this;
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

	public LinkedListOrg(Object element) {
		this.element = element;
	}

	public LinkedListOrg(Object element, LinkedListOrg next) {
		this.element = element;
		this.next = next;
	}

	public Object getElement() {
		return element;
	}

	public LinkedListOrg getNext() {
		return next;
	}

	public void setNext(LinkedListOrg next) {
		this.next = next;
	}

	public void addAll(Object... objects) {
		LinkedListOrg it = this;
		while (it.getNext() != null) {
			it = it.getNext();
		}
		for (int i = 0; i < objects.length; i++) {
			it.setNext(new LinkedListOrg(objects[i]));
			it = it.getNext();
		}
	}

	public void add(Object element) {
		LinkedListOrg it = this;
		while (it.getNext() != null) {
			it = it.getNext();
		}

		it.setNext(new LinkedListOrg(element));

	}

	public int size() {
		int count = 0;
		LinkedListOrg it = this;
		while (it != null) {
			it = it.getNext();
			count++;
		}
		return count;
	}

	@Override
	public LinkedListOrg clone() {
		try {
			LinkedListOrg obj = (LinkedListOrg) super.clone();
			/**
			 * 次の参照をコピーする
			 */
			if (obj.next != null) {
				obj.next = next.clone();
			}
			return obj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}

	}

}
