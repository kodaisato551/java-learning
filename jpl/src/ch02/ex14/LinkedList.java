package ch02.ex14;

/**
 *ex12と同様のソースコード
 *
 * getterはこのクラスでのみの使用を想定している場合は不要。外部でそのフィールドを使用する場合は必要
 * リスト内の要素（element)は一度生成した後に変更されるべきではないのでfinalにして、Setterを用意しない。
 * nextはLinkedListのインスタンスをつなげる際に使用する用途でsetterを提供する。
 *
 */
public class LinkedList {

	private final Object element;
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
