package ch14.ex08;

/**
 * TODO みなおし
 * @author Sato Kodai
 *
 */
public class Friendly {
	private static final Object lock = new Object();
	private Friendly partner;
	private String name;

	public Friendly(String name) {
		this.name = name;
	}

	public void hug() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + "in" + name + ".hug() tryint to invoke "
					+ partner.name + ".hugBack()");
			partner.hugBack();
		}
	}

	private void hugBack() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + "in" + name + ".hugBack()");
		}
	}

	public void becomeFriend(Friendly partner) {
		this.partner = partner;
	}
}
