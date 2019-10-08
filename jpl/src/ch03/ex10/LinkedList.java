package ch03.ex10;

/**
 *リスト内の要素数を返すメソッド実装
 *size()
 */
public class LinkedList implements Cloneable {

	private Object element;
	private LinkedList next;

	public static void main(String[] args) {
		LinkedList l2 = new LinkedList("v2", null);
		LinkedList l1 = new LinkedList("v1", l2);
		l1.addAll("v3", "v4");

		System.out.println(l1.size());
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

	public void add(Object element) {
		LinkedList it = this;
		while (it.getNext() != null) {
			it = it.getNext();
		}

		it.setNext(new LinkedList(element));

	}

	public int size() {
		int count = 0;
		LinkedList it = this;
		while (it != null) {
			it = it.getNext();
			count++;
		}
		return count;
	}

	/**
	 * TODO クローンの実装
	 * むっずい
	 */
	@Override
	public LinkedList clone() {
		try {
			LinkedList obj = (LinkedList) super.clone();
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
